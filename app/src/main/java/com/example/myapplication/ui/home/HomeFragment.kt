package com.example.myapplication.ui.home

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.locationmanager.LocationManager
import com.example.myapplication.managers.SharedPreferencesManager
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),View.OnClickListener{
    private val TAG = this@HomeFragment.toString()
    private val currentUser = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var btnReadMe :Button
    private lateinit var btnFirstEvent :Button
    private lateinit var btnSecondEvent :Button
    private lateinit var btnThirdEvent :Button
    private lateinit var btnFourthEvent :Button
    private lateinit var btnFivthEvent :Button
    private lateinit var btnFrench :Button
    private lateinit var btnItaly : Button
    private lateinit var btnIndian :Button
    private lateinit var locationManager :LocationManager
    private lateinit var location: Location
    private lateinit var currentLocation: LatLng





    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        btnReadMe= root.findViewById(R.id.btnReadMore)
        btnFirstEvent = root.findViewById(R.id.btnFirstEvent)
        btnSecondEvent = root.findViewById(R.id.btnSecondEvent)
        btnThirdEvent =  root.findViewById(R.id.btnThirdEvent)
        btnFourthEvent= root.findViewById(R.id.btnFourthEvent)
        btnFivthEvent = root.findViewById(R.id.btnFivthEvent)
        btnFrench = root.findViewById(R.id.frenchStayOverBtn)
        btnItaly = root.findViewById(R.id.italyStayOverBtn)
        btnIndian = root.findViewById(R.id.indianStayOverBtn)


        btnReadMe.setOnClickListener(this)
        btnFivthEvent.setOnClickListener(this)
        btnFourthEvent.setOnClickListener(this)
        btnThirdEvent.setOnClickListener(this)
        btnSecondEvent.setOnClickListener(this)
        btnFirstEvent.setOnClickListener(this)
        btnFrench .setOnClickListener(this)
        btnItaly.setOnClickListener(this)
        btnIndian.setOnClickListener(this)

        currentLocation = LatLng(0.0,0.0)

        return root
    }

    override fun onClick(v: View?) {
        if (v!=null){
            when(v.id){
                R.id.btnReadMore->{
                    //navigate to about us page
                    findNavController().navigate(R.id.nav_about)
                }
                R.id.btnFirstEvent->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.HOST_NAME, txtFirstEvent.text.toString())
                    val currentEvent = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentEvent.toString() )
                    findNavController().navigate(R.id.eventFragment)
                }
                btnSecondEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtSecondEvent.text.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnThirdEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtThirdEvent.text.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnFourthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFourthEvent.text.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnFivthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFivthEvent.text.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                italyStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.STAY_OVER_NAME, italyText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }
                frenchStayOverBtn.id->{

                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.CULTURE, frenchText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }
                indianStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.STAY_OVER_NAME, indianText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }


            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            LocationManager.LOCATION_PERMISSION_REQUEST_CODE ->{
                LocationManager.locationPermissionsGranted = (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                if(LocationManager.locationPermissionsGranted){
                    //we can fetch the location
                    this.getLastLocation()
                }
            }
        }
    }


    private fun getLastLocation (){
        this.locationManager.getlastLocation()?.observe(this,{loc : Location? ->
            if(loc != null){
                this.location = loc
                SharedPreferencesManager.write(SharedPreferencesManager.LONG_LOCATION , loc.longitude.toString())
                SharedPreferencesManager.write(SharedPreferencesManager.LATIT_LOCATION , loc.latitude.toString())

//                val d = SharedPreferencesManager.read(SharedPreferencesManager.LONG_LOCATION,"")
//
//                Log.e(TAG , "CURRENTlocationLongtituide " + d)
                //display the location in the map by saving it in the sared prefrence
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager = LocationManager(this.requireActivity())
                if(LocationManager.locationPermissionsGranted){
            this.getLastLocation()
        }

    }


}