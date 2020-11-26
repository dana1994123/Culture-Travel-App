package com.example.myapplication.models

import android.media.Image
import com.squareup.moshi.JsonClass
import java.sql.SQLTransactionRollbackException
import java.util.*

@JsonClass(generateAdapter = true)
data class Event (
    var id: String = UUID.randomUUID().toString(),
    var name : String = "",
    var location :String = "",
    var duration :String = "" ,
    var language :String = "",
    var desc : String = "",
    var price : Long = 0,
    var icon1 : String="",
    var icon2 : String = ""
)