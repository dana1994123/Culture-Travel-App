package com.example.myapplication.database

import android.util.Log
import com.example.myapplication.models.StayOver
import com.example.myapplication.models.Event
import com.example.myapplication.models.Guest
import com.example.myapplication.models.Payment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repo {
    private val db = Firebase.firestore
    private val  COLLECTION_ONE = "Guest"
    private val COLLECTION_TWO = "Appointment"
    private val COLLECTTION_THREE = "Event"
    private val TAG = this.toString()



    fun addGuest(guest: Guest){
        db.collection(COLLECTION_ONE).document(guest.id.toString()).set(guest)
            .addOnSuccessListener { Log.e(TAG , "Guest DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Guest DOC Failure") }
    }


    fun fetchAllGuest() : CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTION_ONE)
        Log.e ("Collection refrence :" , collectionRefrence.id)
        return  collectionRefrence
    }



    fun deleteGuest(email:String){

        db.collection(COLLECTION_ONE).document(email).delete()
            .addOnSuccessListener { Log.e(TAG , "Guest DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Guest DOC Failure") }
    }




    fun addAppointment(appointment :StayOver){
        db.collection(COLLECTION_TWO).document(appointment.id.toString()).set(appointment)
            .addOnFailureListener { error -> Log.e(TAG , "Appointment Doc failed ") }
            .addOnSuccessListener { Log.e(TAG ,"Appointment Doc success" ) }
    }


    fun deleteAppointment(appoID :String ){
        db.collection(COLLECTION_TWO).document(appoID).delete()
            .addOnSuccessListener {
                Log.e(TAG,"DELETE appointment Done")
            }
            .addOnFailureListener{
                    error -> Log.e (TAG , "DELETE appointment FAiled")
            }


    }
    fun fetchAllAppointment() : CollectionReference{
        val collectionRefrence :CollectionReference = db.collection(COLLECTION_TWO)
        Log.e("Collection refrence :", collectionRefrence.id)

        return collectionRefrence
    }



    fun addEvent (event : Event){
        db.collection(COLLECTTION_THREE).document(event.id.toString()).set(event)
            .addOnSuccessListener { Log.e(TAG , "Event DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Event DOC Failure") }

    }

}