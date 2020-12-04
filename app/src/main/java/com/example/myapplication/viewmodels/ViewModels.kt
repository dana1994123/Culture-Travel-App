package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.Repo
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ViewModels : ViewModel() {
    private val repo = Repo()
    private val TAG  = this@ViewModels.toString()
    private val hostName = SharedPreferencesManager.read(SharedPreferencesManager.HOST_NAME,"").toString()
    private val guestEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
    var guestList: MutableLiveData<List<Guest>> = MutableLiveData()
    private val stayOverCulture = SharedPreferencesManager.read(SharedPreferencesManager.CULTURE,"").toString()
    var eventList: MutableLiveData<List<Event>> = MutableLiveData()
    var eventBookingsList: MutableLiveData<List<BookingEvent>> = MutableLiveData()
    var hostList: MutableLiveData<List<Host>> = MutableLiveData()
    var stayOverList: MutableLiveData<List<StayOverBooking>> = MutableLiveData()
    //we have to save the event name in the shared prefrence so we can use it to fetch event name
    private val eventName  = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME,"").toString()
    private val stayOverName  = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME,"").toString()

    fun addGuest(guest :Guest){
        repo.addGuest(guest)

        Log.e(TAG , "addGuest"  + guest.toString())
    }


    fun deleteGuest (email:String ){
        repo.deleteGuest(email)
    }
//    fun updateGuest(name:String,phoneNumber:String,language:String){
//        repo.updateGuest(name,guestEmail,phoneNumber,language)
//    }
    fun updateGuest2(currentGuest : Guest){
        repo.updateGuest2(currentGuest )
    }
    fun getAllHosts(){
        repo.fetchAllHosts()
            .whereEqualTo("name",hostName)
            .orderBy("maximumGuests", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                var modifiedHostList : MutableList<Host> = mutableListOf()
                if(error != null){
                    Log.e(TAG,"Listening failed. No connection available")
                    this.hostList.value = null
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    for (documentChange in snapshot.documentChanges){
                        val host = documentChange.document.toObject(Host::class.java)
//                        Log.e(TAG, "Parking document change : " + parking.toString())

                        when (documentChange.type){
                            DocumentChange.Type.ADDED -> {
                                modifiedHostList.add(host)
                                Log.e(TAG, "Document added to collection " + host.toString())
                            }
                            DocumentChange.Type.MODIFIED -> {


                                Log.e(TAG, "Document modified " + host.toString())
                            }
                            DocumentChange.Type.REMOVED -> {
                                modifiedHostList.remove(host)
                                Log.e(TAG, "Document deleted " + host.toString())
                            }
                        }

                    }
                    this.hostList.value = modifiedHostList
                }else{
                    Log.e(TAG, "Current data is null")
                }
            }
    }
    fun fetchStayoverByCulture(){
        repo.fetchAlStayOver()
            .whereEqualTo("culture",stayOverCulture)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    Log.e(TAG, "LISTING FAILED")
                    this.stayOverList.value = null
                    return@addSnapshotListener
                }
                var modifiedStayOver: MutableList<StayOverBooking> = mutableListOf()

                if (snapshot != null) {
                    for (documentChange in snapshot.documentChanges) {
                        var stayOver = documentChange.document.toObject(StayOverBooking::class.java)
                        Log.e(TAG, "stay ovaer CHANGED ")


                        when (documentChange.type) {
                            DocumentChange.Type.ADDED -> {
                                modifiedStayOver.add(stayOver)
                                Log.e(TAG, "stay ovaer DOC added ")
                            }
                            DocumentChange.Type.MODIFIED -> {

                                Log.e(TAG, "stay ovaer DOC modified ")

                            }
                            DocumentChange.Type.REMOVED -> {
                                modifiedStayOver.remove(stayOver)
                                Log.e(TAG, "stay ovaer DOC removed")
                            }
                        }
                    }
                    this.stayOverList.value = modifiedStayOver
                } else {
                    Log.e(TAG, "Guest not found")
                }
            }

    }
    fun fetchAllGuest(){
        repo.fetchAllGuest()
            .whereEqualTo("email" , guestEmail)
            .addSnapshotListener{snapshot , error ->
                var modifiedGuestList: MutableList<Guest> = mutableListOf()
                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var modifiedGuest = documentChange.document.toObject(Guest::class.java)
                        Log.e(TAG, "GUEST DOC CHANGED ")
                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                modifiedGuestList.add(modifiedGuest)
                                this.guestList.value = modifiedGuestList
                                Log.e(TAG, "GUEST DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{

                                Log.e(TAG, "GUEST DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                modifiedGuestList.remove(modifiedGuest)
                                Log.e(TAG, "GUEST DOC removed")
                            }
                        }
                    }

                    }else{
                        Log.e(TAG,"Guest not found")
                    }
                }

            }
    fun fetchAllEvent(){
        repo.fetchAlEvent()
            .whereEqualTo("name" , eventName)
            .addSnapshotListener{snapshot , error ->

                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    this.eventList.value = null
                    return@addSnapshotListener
                }
                var modifiedEventList: MutableList<Event> = mutableListOf()

                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var event = documentChange.document.toObject(Event::class.java)
                        Log.e(TAG, "event DOC CHANGED ")
                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                modifiedEventList.add(event)
                                Log.e(TAG, "event DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{

                                Log.e(TAG, "event DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                modifiedEventList.remove(event)
                                Log.e(TAG, "event DOC removed")
                            }
                        }
                    }
                    this.eventList.value = modifiedEventList
                }else{
                    Log.e(TAG,"Guest not found")
                }
            }

    }

    //method to book the event add it to the guest booking event
    fun addBookingEvent(bookingEvent :BookingEvent){
        repo.addBookingEvent(bookingEvent)

        Log.e(TAG , "addbookingEvent"  + bookingEvent.toString())
    }


    //method to get all the guest booking event in the booking list fragment when we create a reycler view
    fun getBookingEvent(){
        repo.getBookingEvent()
            .whereEqualTo("email" , guestEmail)
            .addSnapshotListener{snapshot , error ->
                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    this.eventBookingsList.value = null
                    return@addSnapshotListener
                }
                var modifiedBookingEventList: MutableList<BookingEvent> = mutableListOf()

                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var bookingEvent = documentChange.document.toObject(BookingEvent::class.java)
                        Log.e(TAG, "event DOC CHANGED ")
                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                modifiedBookingEventList.add(bookingEvent)
                                Log.e(TAG, "event DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{

                                Log.e(TAG, "event DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                modifiedBookingEventList.remove(bookingEvent)
                                Log.e(TAG, "event DOC removed")
                            }
                        }
                    }
                    this.eventBookingsList.value = modifiedBookingEventList
                }else{
                    Log.e(TAG,"BOOKING EVENT not found")
                }
            }

    }
    fun deleteBookingEvent(eventId: String){
        repo.deleteBookingEvent(eventId)
    }


    fun fetchAllStayOver(){
        repo.fetchAlStayOver()
            .whereEqualTo("name" , stayOverName)
            .addSnapshotListener{snapshot , error ->

                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    this.stayOverList.value = null
                    return@addSnapshotListener
                }
                var modifiedStayOver: MutableList<StayOverBooking> = mutableListOf()

                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var stayOver = documentChange.document.toObject(StayOverBooking::class.java)
                        Log.e(TAG, "stay ovaer CHANGED ")


                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                modifiedStayOver.add(stayOver)
                                Log.e(TAG, "stay ovaer DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{

                                Log.e(TAG, "stay ovaer DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                modifiedStayOver.remove(stayOver)
                                Log.e(TAG, "stay ovaer DOC removed")
                            }
                        }
                    }
                    this.stayOverList.value = modifiedStayOver
                }else{
                    Log.e(TAG,"Guest not found")
                }
            }

    }
}