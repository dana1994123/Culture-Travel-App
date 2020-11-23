package com.example.myapplication.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.models.Event
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var gson :Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gson = Gson()

        val event1 = Event("indian foof" ,"Toronto" , "2" , "english" , "sss" , "dddd" , 5 , "hkghj")

        var json :String = gson.toJson(event1)







    }
}