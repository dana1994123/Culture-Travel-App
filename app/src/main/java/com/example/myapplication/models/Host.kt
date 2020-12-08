package com.example.myapplication.models

import java.util.*
import kotlin.collections.ArrayList

data class Host (
    var id: String = UUID.randomUUID().toString(),
    var email: String = "",
    var name :String = "",
    var about :String = "",
    var location :String ="",
    var workExperience :String = "",
    var img :String =""
)