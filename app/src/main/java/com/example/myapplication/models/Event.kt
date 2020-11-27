package com.example.myapplication.models

import android.media.Image
import com.squareup.moshi.JsonClass
import java.sql.SQLTransactionRollbackException
import java.util.*

data class Event (
    var id :String = UUID.randomUUID().toString(),
    var name : String ="",
    var location :String = "",
    var duration :String = "" ,
    var language :String = "",
    var cate : String = "",
    var icon1 : String ="",
    var icon2 : String="",
    var date :String="",
    var hostName :String = ""
)