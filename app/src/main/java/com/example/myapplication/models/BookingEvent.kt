package com.example.myapplication.models

import android.provider.ContactsContract
import java.util.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-11-26
*/
data class BookingEvent (
    var id :String = UUID.randomUUID().toString(),
    var email: String = "",
    var event :Event = Event()
)