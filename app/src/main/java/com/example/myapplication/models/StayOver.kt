package com.example.myapplication.models

import java.util.*

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-12-04
*/class StayOver (
    var id :String = UUID.randomUUID().toString(),
    var stayOverName :String ="",
    var maxDuration: String = "",
    var dates :Array<String> = arrayOf(),
    var culture: String = "",
    var price :String = "",
    var maxAdult :String ="",
    var maxChild :String ="",
    var host :Host = Host(),
    var img1 :String =" ",
    var img2 :String ="",
    var img3 :String =""

)