package com.example.myapplication.models

import android.media.Image
import java.sql.SQLTransactionRollbackException
import java.util.*

data class Event (
    var id :String = UUID.randomUUID().toString(),
    var name : String ="",
    var location :String = "",
    var duration :String = "" ,
    var language :String = "",
    var desc : String = "",
    var price : Long = 0,
    var icon1 : Image,
    var icon2 : Image
)