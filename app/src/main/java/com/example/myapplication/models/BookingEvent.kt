package com.example.myapplication.models

import java.util.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-11-26
*/
data class BookingEvent (
    var id :String = UUID.randomUUID().toString(),
    var email :String ="",
    var eventName : String ="",
    var eventLocation :String = "",
    var eventDuration :String = "",
    var eventLanguage :String = "",
    var eventDate :String =""
)