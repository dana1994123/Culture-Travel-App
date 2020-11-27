package com.example.myapplication.database

import android.util.Log
import com.example.myapplication.models.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repo {
    private val db = Firebase.firestore
    private val  COLLECTION_ONE = "Guest"
    private val COLLECTION_TWO = "BookingHistory"
    private val COLLECTTION_THREE = "Event"
    private val COLLECTTION_FOUR = "StayOver"

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





    fun bookEvent (bookingEvent : BookingEvent){
        db.collection(COLLECTTION_THREE).document(bookingEvent.id.toString()).set(bookingEvent)
            .addOnSuccessListener { Log.e(TAG , "Event DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Event DOC Failure") }
    }

    fun fetchBookingEvent(): CollectionReference{
        val collectionReference :CollectionReference = db.collection(COLLECTION_TWO)
        return collectionReference
    }


    fun fetchAlEvent() : CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTTION_THREE)
        Log.e ("Collection refrence :" , collectionRefrence.id)
        return  collectionRefrence
    }


    fun fetchAlStayOver(): CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTTION_FOUR)
        Log.e ("Collection refrence :" , collectionRefrence.id)
        return  collectionRefrence

    }

}