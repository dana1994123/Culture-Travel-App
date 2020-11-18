package com.example.myapplication.models

import java.util.*


data class Payment(
    var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var email: String = "",
    var phoneNum: Int? = null,
    var cardNum: String = "",
    var nameOnCard: String = "",
    var cvv: Int? = null,
    var expiry: String = ""
) {
}