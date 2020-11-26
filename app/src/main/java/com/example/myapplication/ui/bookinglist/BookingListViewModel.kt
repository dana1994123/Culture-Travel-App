package com.example.myapplication.ui.bookinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
user dana
Student ID  991541439
Name : Dana Aljamal 
Date 2020-11-26
*/
class BookingListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}