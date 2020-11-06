package com.example.myapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myapplication.database.GuestRepo
import com.example.myapplication.models.Guest

class ViewModels : ViewModel() {
    private val guestRepo = GuestRepo()


    fun addGuest(guest :Guest){
        guestRepo.addGuest(guest)
    }
}