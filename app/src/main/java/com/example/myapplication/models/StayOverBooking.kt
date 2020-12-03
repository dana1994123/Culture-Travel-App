package com.example.myapplication.models

import java.util.*

data class StayOverBooking (
        var guestEmail :String = "",
        var startDate: String="",
        var hostName : String = "",
        var maxDuration: String = ""
)