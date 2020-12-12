package com.example.myapplication.models

import android.net.Uri
import java.net.URI
import java.util.*

data class Guest (
        var id: String = UUID.randomUUID().toString(),
        var name :String ="",
        var email:String = "",
        var password :String = "",
        var phoneNumber : String? ="",
        var language : String? = "",
        var verifiedStatus :Boolean = false,
        var verifiedImage : String = "",
        var profileImg :String = ""
)