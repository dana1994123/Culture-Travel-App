package com.example.myapplication.models

import java.util.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-12-11
*/
class StayOverBooking (
    var id: String = UUID.randomUUID().toString(),
    var guestEmail :String ="",
    var culture :String = "",
    var img1Stay :String ="",
    var hostName :String ="" ,
    var bookingDate :String = "",
    var stayGuestNumber:String = "",
    var total :String =""
)