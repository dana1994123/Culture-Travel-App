package com.example.myapplication.locationmanager

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

class LocationManager(var context :Context) {
    private val TAG = this.toString()

    //perform further services
    var fusedLocationProviderClient : FusedLocationProviderClient? = null

    //get the location from the provider and cause it is changeble so mutable live data
    private var location :MutableLiveData<Location> = MutableLiveData()
    //initiate recieving the location service we have to request thta
    lateinit var locationRequest : LocationRequest



    companion object{
        const val LOCATION_PERMISSION_REQUEST_CODE = 102
        var locationPermissionsGranted = false
    }
    init{

        initialiseCheckLocation()
        getLocationProvider ()
        Log.e("permision status " , locationPermissionsGranted.toString())
    }

    fun getLocationProvider(): FusedLocationProviderClient{
        if(fusedLocationProviderClient == null){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        }
        return fusedLocationProviderClient!!
    }


    private fun initialiseCheckLocation(){

        //check if the locationPermission granted or not :
        locationPermissionsGranted = (ContextCompat.checkSelfPermission(
                this.context.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)


        //we will request the permission if our flag is false :
        if (!locationPermissionsGranted){
            //request the location permission
            requestLocationPermission()
        }
    }


    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(this.context as Activity ,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION),
        LOCATION_PERMISSION_REQUEST_CODE)

        Log.e("permision status " , locationPermissionsGranted.toString())
    }

    fun getlastLocation() : LiveData<Location>?{
        if (locationPermissionsGranted){
            try{
                this.fusedLocationProviderClient!!.lastLocation
                    .addOnSuccessListener {loc : Location? ->
                        if(loc != null){
                            //post the loc to live data
                            location.value = loc }
                    }
                    .addOnFailureListener { Log.e(TAG , "ERROR GETING LAST LOCATION")  }


            }
            catch (ex :SecurityException){
                Log.e(TAG,"SECURITY EXCEPTION :" + ex.localizedMessage)
            }
            return location

        }
        return null
    }



}