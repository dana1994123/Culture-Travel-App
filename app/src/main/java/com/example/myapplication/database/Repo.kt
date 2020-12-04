package com.example.myapplication.database

import android.util.Log
import com.example.myapplication.models.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repo {
    private val db = Firebase.firestore
    private val  COLLECTION_ONE = "Guest"
    private val COLLECTION_TWO = "BookingHistory"
    private val COLLECTTION_THREE = "Event"
    private val COLLECTTION_FOUR = "StayOver"
    private val COLLECTION_FIVE = "Host"
    private val COLLECTION_SIX = "BookingEvent"
    private val TAG = this.toString()

    fun addHost(host: Host){
        db.collection(COLLECTION_FIVE).document(host.id.toString()).set(host)
            .addOnSuccessListener { Log.e(TAG , "Host DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Host DOC Failure") }
    }

    fun addGuest(guest: Guest){
        db.collection(COLLECTION_ONE).document(guest.id.toString()).set(guest)
            .addOnSuccessListener { Log.e(TAG , "Guest DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"Guest DOC Failure") }
    }

    fun addStayOver(stayOver: StayOver){
        db.collection(COLLECTTION_FOUR).document(stayOver.id.toString()).set(stayOver)
            .addOnSuccessListener { Log.e(TAG , "STAYOVER DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"STAYOVER DOC Failure") }
    }


    fun fetchAllGuest() : CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTION_ONE)
        Log.e ("Collection refrence :" , collectionRefrence.id)
        return  collectionRefrence
    }
    fun fetchAllHosts() : CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTION_FIVE)
        Log.e ("Collection refrence :" , collectionRefrence.id)
        return  collectionRefrence
    }
//    fun updateGuest(name:String,email:String,phoneNumber:String,language:String) {
//        val updatedUser = db.collection(COLLECTION_ONE).document(email)
//        updatedUser.update("name",name)
//            .addOnSuccessListener { Log.d(TAG,"name successfully updated") }
//            .addOnFailureListener { e -> Log.w(TAG,"Error updating the name",e) }
//
//        updatedUser.update("language",language)
//            .addOnSuccessListener { Log.d(TAG,"language successfully updated") }
//            .addOnFailureListener { e -> Log.w(TAG,"Error updating the language",e) }
//
//        updatedUser.update("phoneNumber",phoneNumber)
//            .addOnSuccessListener { Log.d(TAG,"phoneNumber successfully updated") }
//            .addOnFailureListener { e -> Log.w(TAG,"Error updating the phoneNumber",e) }
//    }

    fun updateGuest2(currentGuest : Guest){
        db.collection(COLLECTION_ONE)
            .document(currentGuest.id)
            .update("name" , currentGuest.name , "language" , currentGuest.language , "phoneNumber" , currentGuest.phoneNumber)
            .addOnSuccessListener { Log.e(TAG, "Document successfully deleted") }
            .addOnFailureListener{error -> Log.e(TAG, "Unable to delete a document" + error.localizedMessage)}
    }


    fun addBookingEvent(bookingEvent: BookingEvent){
        db.collection(COLLECTION_SIX).document(bookingEvent.id.toString()).set(bookingEvent)
            .addOnSuccessListener { Log.e(TAG , "bookingEvent DOC succseefully added") }
            .addOnFailureListener { error -> Log.e(TAG,"bookingEvent DOC Failure") }
    }

    fun getBookingEvent() : CollectionReference{
        val collectionRefrence  :CollectionReference = db.collection(COLLECTION_SIX)
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


    fun deleteBookingEvent(id : String){
        db.collection(COLLECTION_SIX)
            .document(id)
            .delete()
            .addOnSuccessListener { Log.e(TAG, "Document successfully deleted") }
            .addOnFailureListener{error -> Log.e(TAG, "Unable to delete a document" + error.localizedMessage)}


    }






}