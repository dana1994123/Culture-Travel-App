package com.example.myapplication.models

data class Host (
    var email: String = "",
    var name :String = "" ,
    var about :String = "",
    var languageSpoken : String = "",
    var location :String ="",
    var workExperience :String = "",
    var maximumGuests: Int
)