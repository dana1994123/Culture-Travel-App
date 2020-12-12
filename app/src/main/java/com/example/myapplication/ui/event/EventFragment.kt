package com.example.myapplication.ui.event

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.managers.LocationManager
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.BookingEvent
import com.example.myapplication.models.Event
import com.example.myapplication.models.Host
import com.example.myapplication.models.Locations
import com.example.myapplication.ui.event.EventFragment.Companion.existingEvent
import com.example.myapplication.viewmodels.ViewModels
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_event.*
import kotlinx.android.synthetic.main.fragment_event.view.*
import java.net.URI
import java.util.logging.Level.parse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class EventFragment : Fragment() , View.OnClickListener, OnMapReadyCallback{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var edtFirstImage : ImageView
    lateinit var edtSecondImage :ImageView
    lateinit var edtEventName :TextView
    lateinit var edtEventLocation :TextView
    lateinit var edtEventDuration :TextView
    lateinit var edtLang :TextView
    lateinit var edtDate : TextView
    lateinit var edtEventDesc :TextView
    lateinit var btnBooking :Button
    lateinit var viewModel: ViewModels
    var currentEventName = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME, "")
    var currentEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"")
    private var bookingStatus :Boolean = true







    private var map: GoogleMap? = null
    private val DEFAULT_ZOOM : Float = 15.0F  //1: world, 5: landmass/continent, 10: city, 15: streets, 20: building
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationManager : LocationManager
    private lateinit var location: Location
    private lateinit var currentLocObj : Locations
    private lateinit var currentEventObj : Locations

    var locationList : ArrayList<Locations> = arrayListOf()




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        this.locationManager = LocationManager(this.requireContext())





        currentLocObj = Locations(LatLng(0.0, 0.0) , "CurrentLocation")
        currentEventObj = Locations (LatLng(0.0,0.0) , "Event Location")


        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)

        locationList.add(currentLocObj)
        locationList.add(currentEventObj)




        this.addMarkerOnMap(this.locationList)
        if (LocationManager.locationPermissionsGranted){
            this.getLastLocation()
        }

        locationCallback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?){
                locationResult ?: return

                for(location in locationResult.locations){
                    currentLocObj.loc = LatLng(location.latitude, location.longitude)

                    addMarkerOnMap(locationList)

                    Log.e( "current location : " , currentLocObj.loc.toString())
                    Log.e("event location " , currentEventObj.loc.toString())

                }
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =inflater.inflate(R.layout.fragment_event, container, false)
        locationManager = LocationManager(this.requireContext())
        edtFirstImage = root.edtFirstImage
        edtSecondImage = root.edtSecondImage
        edtEventName = root.edtEventName
        edtEventLocation = root.edtEventLocation
        edtEventDuration = root.edtEventDuration
        edtLang = root.edtLang
        edtDate = root.edtDate
        edtEventDesc = root.edtEventDesc
        btnBooking = root.btnBooking
        btnBooking.setOnClickListener(this)

        //this.disableBookingEvent()
        return  root
    }


    override fun onResume() {
        super.onResume()
        locationManager.requestLocationUpdates(locationCallback)
        Log.e("the fragmnt" , "resume")
        viewModel = ViewModels()
        viewModel.fetchAllEvent()
        //create a method to populate the event by fetching the event to the fragment
        this.populateEvent ()

        viewModel.getBookingEvent()
        bookingStatus =true


    }
    override fun onPause() {
        super.onPause()
        locationManager.fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        var existingEvent = Event()
        var bookingEvent = BookingEvent()
    }




    override fun onClick(v: View?) {
        if (v!=null ){
            when(v.id){
                R.id.btnBooking -> {
                    //this.disableBookingEvent()
                    bookingEvent.email = currentEmail.toString()
                    bookingEvent.event = existingEvent
                    viewModel.addBookingEvent(bookingEvent)
                    findNavController().navigate(R.id.bookingConfirmation2)
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
            LocationManager.LOCATION_PERMISSION_REQUEST_CODE -> {
                LocationManager.locationPermissionsGranted = (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)

                if (LocationManager.locationPermissionsGranted){
                    this.getLastLocation()
                }
                return
            }
        }
    }

    private fun getLastLocation(){
        this.locationManager.getLastLocation()?.observe(viewLifecycleOwner, {loc: Location? ->
            if (loc != null){
                this.location = loc
                this.currentLocObj.loc = LatLng(location.latitude, location.longitude)

                Log.e( "current location : " , this.currentLocObj.loc.toString())

                //display the current locations and event location
                this.addMarkerOnMap(this.locationList)
            }
        })
    }




    override fun onMapReady(googleMap: GoogleMap?) {
        if(googleMap!= null){
            this.map = googleMap
            googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isZoomGesturesEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true
            googleMap.uiSettings.isScrollGesturesEnabled = true



            for (i in locationList){
                googleMap.addMarker(
                    MarkerOptions().position(i.loc).title("Marker")
                )
            }
        }

        else{
            Log.e( "Map not ready yet" , "failed")
        }

    }


    private fun addMarkerOnMap(arrayList: ArrayList<Locations>){

        if (this.map != null){
            for(i in locationList){
                this.map!!.addMarker(
                    MarkerOptions().position(i.loc).title("${i.title}")
                )
                this.map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(i.loc, 3.0f))


            }

        }
    }


    private fun populateEvent(){
        //create a method to populate the event by fetching the event to the fragment
        Log.e("collection jftyhjghj", currentEventName.toString())
        this.viewModel.eventList.observe(this.requireActivity(), { eventsList ->
            if (eventsList != null) {
                existingEvent = eventsList[0]
                //fetch the image from the data base
                edtFirstImage.load(existingEvent.icon1)
                edtSecondImage.load(existingEvent.icon2)
            }
            edtEventName.setText(existingEvent.name)
            edtEventLocation.setText(existingEvent.location)
            edtLang.setText(existingEvent.language)
            edtEventDuration.setText(existingEvent.duration)
            edtDate.setText(existingEvent.date)
            edtEventDesc.setText(existingEvent.cate)
            currentEventObj.loc = LatLng(existingEvent.latitLocation.toDouble(), existingEvent.longLocation.toDouble())

        })
    }


    fun disableBookingEvent()  {
        //check if the user has booked this event before and if yes show a toast msg
        viewModel.eventBookingsList.observe(this.requireActivity(), {bookingEventHistory ->
            if(bookingEventHistory != null){
                for(i in bookingEventHistory ){
                    if(i.event.name == currentEventName){
                        //the user has already book the event
                        Toast.makeText(this.requireContext(), "You have already book this event. Please check your booking history", Toast.LENGTH_SHORT).show()
                        bookingStatus = false
                        break
                    }

                }
            }
        })

    }

}