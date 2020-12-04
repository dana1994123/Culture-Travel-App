package com.example.myapplication.models

import java.util.*

data class StayOverBooking (
        var guestEmail :String = "",
        var stayOver: StayOver = StayOver(),
        var bookingConfirmation :Boolean = false
)