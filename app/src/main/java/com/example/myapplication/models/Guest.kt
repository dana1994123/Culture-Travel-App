package com.example.myapplication.models

import java.util.*

data class Guest (
        //add the id
        var id: String = UUID.randomUUID().toString(),
        var name :String ="",
        var email:String = "",
        var password :String = "",
        var phoneNumber : String? ="",
        var language : String? = ""

)