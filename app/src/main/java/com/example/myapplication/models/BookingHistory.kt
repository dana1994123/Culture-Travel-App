package com.example.myapplication.models

import java.util.*

data class EventBookingHistory (
    var id :String = UUID.randomUUID().toString(),
    var email :String ="",
    var name : String ="",
    var location :String = "",
    var duration :String = "" ,
    var language :String = "",
    var date :String =""
)