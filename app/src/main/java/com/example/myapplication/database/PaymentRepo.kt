package com.example.myapplication.database

import android.util.Log
import com.example.myapplication.models.Payment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PaymentRepo {
    private val COLLECTION_NAME = "Payments"
    private val db = Firebase.firestore
    private val TAG: String = this.toString()

    fun addPaymentMethod(payment: Payment){
        var status: String = ""
        db.collection(COLLECTION_NAME)
            .document(payment.id.toString())
            .set(payment)
            .addOnSuccessListener { Log.e(TAG,"Document added successfully")
            status = "Success"
            }
            .addOnFailureListener {error ->
                Log.e(TAG,"Unable to add a document ${error.localizedMessage}")
            status = error.localizedMessage.toString()
            }
    }
    fun getAllPayments():CollectionReference{
        val collectionReference = db.collection(COLLECTION_NAME)
        Log.e(TAG,"Collection Reference ${collectionReference.id}")
        return collectionReference
    }
}