package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.Repo
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Event
import com.example.myapplication.models.StayOver
import com.example.myapplication.models.Guest
import com.example.myapplication.models.Payment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ViewModels : ViewModel() {
    private val repo = Repo()
    private val TAG  = this@ViewModels.toString()
    private val guestEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()

    var guest: MutableLiveData<Guest> = MutableLiveData()

    fun addGuest(guest :Guest){
        repo.addGuest(guest)

        Log.e(TAG , "addGuest"  + guest.toString())
    }

    fun deleteGuest (email:String ){
        repo.deleteGuest(email)
    }


    fun fetchAllGuest(){
        repo.fetchAllGuest()
            .whereEqualTo("email" , guestEmail)
            .addSnapshotListener{snapshot , error ->
                var modifiedGuest: Guest? = null
                if (error != null){
                    Log.e(TAG, "LISTING FAILED")
                    return@addSnapshotListener
                }
                if (snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        modifiedGuest= documentChange.document.toObject(Guest::class.java)
                        Log.e(TAG, "GUEST DOC CHANGED ")
                        when(documentChange.type){
                            DocumentChange.Type.ADDED ->{
                                Log.e(TAG, "GUEST DOC added ")
                            }
                            DocumentChange.Type.MODIFIED->{
                                Log.e(TAG, "GUEST DOC modified ")

                            }
                            DocumentChange.Type.REMOVED ->{
                                Log.e(TAG, "GUEST DOC removed")
                            }
                        }
                    }
                    if(modifiedGuest!=null){
                        guest.value = modifiedGuest
                    }else{
                        Log.e(TAG,"Guest not found")
                    }
                }

                else {
                    Log.e(TAG, "CURRENT DATA IS NULL")
                }

            }
    }




    fun addAppointment (appointment: StayOver){
        repo.addAppointment(appointment)
        Log.e(TAG , "addAppointment"  + appointment.toString())
    }


    fun fetcAllAppointment(){
        repo.fetchAllAppointment()
            .whereEqualTo("email" , guestEmail)
            .orderBy("eventDate" , Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e(TAG, "LISTING FAILED")
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    for (documentChange in snapshot.documentChanges) {

                        val parking = documentChange.document.toObject(StayOver::class.java)
                        Log.e(TAG, "Appointment DOC CHANGED ")

                        when (documentChange.type) {
                            DocumentChange.Type.ADDED -> {

                                Log.e(TAG, "Appointment DOC added ")

                            }
                            DocumentChange.Type.MODIFIED -> {
                                Log.e(TAG, "Appointment DOC modified ")

                            }
                            DocumentChange.Type.REMOVED -> {
                                Log.e(TAG, "Appointment DOC removed")
                            }
                        }
                    }

                } else {
                    Log.e(TAG, "CURRENT DATA IS NULL")

                }
            }
    }


    fun deleteAppointment (appointId: String){
        repo.deleteAppointment(appointId)

    }
}