package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.PaymentRepo
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Payment
import com.google.firebase.firestore.DocumentChange

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-11-26
*/
class PaymentViewModel: ViewModel() {
    private val TAG = this@PaymentViewModel.toString()
    private val paymentRepo = PaymentRepo()
    private var paymentList: MutableLiveData<List<Payment>> = MutableLiveData()
    fun addPayment(payment: Payment){
        val status = paymentRepo.addPaymentMethod(payment)
        Log.e("payment view model","addPayment status ${status}")
        Log.e("payment view model", "addPayment ${payment.toString()}")
    }
    fun getAllPayments(){
        val userEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()

        paymentRepo.getAllPayments()
            .whereEqualTo("email",userEmail)
            .addSnapshotListener{ snapshot, error->
                var modifiedPaymentList : MutableList<Payment> = mutableListOf()
                if(error != null){
                    Log.e(TAG,"Listening failed. No connection available")
                    this.paymentList.value = null
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    for(documentChange in snapshot.documentChanges){
                        val payment = documentChange.document.toObject(Payment::class.java)

                        when(documentChange.type){
                            DocumentChange.Type.ADDED  ->{
                                modifiedPaymentList.add(payment)
                                Log.e(TAG,"Document added to the collection ${payment.toString()}")
                            }
                        }
                    }
                    this.paymentList.value = modifiedPaymentList
                }else{
                    Log.e(TAG, "Current data is null")
                }

            }
    }

}