package com.example.myapplication.models

import java.util.*

data class Host (
    var id: String = UUID.randomUUID().toString(),
    var email: String = "",
    var name :String = "",
    var about :String = "",
    var culture: String = "",
    var languageSpoken : String = "",
    var location :String ="",
    var maximumGuests: String = "",
    var workExperience :String = "",
)