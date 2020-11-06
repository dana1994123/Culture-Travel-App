package com.example.myapplication.database

import com.example.myapplication.models.Guest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GuestRepo {
    private val db = Firebase.firestore
    private val  COLLECTION_NAME = "Guest"
    private val TAG = this.toString()



    fun addGuest(guest: Guest){
        db.collection(COLLECTION_NAME).document(guest.id.toString()).set(guest)
    }

}