package com.example.myapplication.models

import java.util.*

data class StayOverBooking (
        var id :String = UUID.randomUUID().toString(),
        var guestEmail :String = "",
        var stayOver: StayOver = StayOver(),
        var total :Double = 0.0
)