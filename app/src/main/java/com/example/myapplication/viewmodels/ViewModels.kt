package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.Repo
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Event
import com.example.myapplication.models.StayOver
import com.example.myapplication.models.Guest
import com.example.myapplication.models.Host
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ViewModels : ViewModel() {
    private val repo = Repo()
    private val TAG  = this@ViewModels.toString()
    private val guestEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
    var guestList: MutableLiveData<List<Guest>> = MutableLiveData()
    var eventList: MutableLiveData<List<Event>> = MutableLiveData()
    var hostList: MutableLiveData<List<Host>> = MutableLiveData()
    var stayOverList: MutableLiveData<List<StayOver>> = MutableLiveData()
    //we have to save the event name in the shared prefrence so we can use it to fetch event name
    private val eventName  = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
    private val stayOverName  = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()

    fun addGuest(guest :Guest){
        repo.addGuest(guest)

        Log.e(TAG , "addGuest"  + guest.toString())
    }


    fun deleteGuest (email:String ){
        repo.deleteGuest(email)
    }
    fun fetchAllHosts(){
        repo.fetchAllHosts()
            .addSnapshotListener{snapshot , error ->
                var modifiedHostList: MutableList<Host> = mutableListOf()
                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var modifiedHost = documentChange.document.toObject(Host::class.java)
                        Log.e(TAG, "Host DOC CHANGED ")
                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                modifiedHostList.add(modifiedHost)
                                Log.e(TAG, "Host DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{

                                Log.e(TAG, "Host DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                modifiedHostList.remove(modifiedHost)
                                Log.e(TAG, "Host DOC removed")
                            }
                        }
                    }
                    this.hostList.value = modifiedHostList
                }else{
                    Log.e(TAG,"Host not found")
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
                    guestList.value = modifiedGuestList
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



    fun fetchAllStayOver(){
        repo.fetchAlStayOver()
            .whereEqualTo("name" , stayOverName)
            .addSnapshotListener{snapshot , error ->

                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    this.stayOverList.value = null
                    return@addSnapshotListener
                }
                var modifiedStayOver: MutableList<StayOver> = mutableListOf()

                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        var stayOver = documentChange.document.toObject(StayOver::class.java)
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