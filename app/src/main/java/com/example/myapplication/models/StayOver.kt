package com.example.myapplication.models

import java.util.*

data class StayOver (
        var id  : String = UUID.randomUUID().toString(),
        var email :String = "",
        var date :String = "",
        var host : String = ""
)