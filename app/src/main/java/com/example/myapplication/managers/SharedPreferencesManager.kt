package com.example.myapplication.managers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {


    private var sharedPreferences: SharedPreferences? = null

    val EMAIL = "KEY_EMAIL"
    val PASSWORD = "KEY_PASSWORD"
    val EVENT_NAME = "KEY_EVENT"
    val HOST_NAME = "HOST_NAME"
    val LONG_LOCATION = "LONG_LOCATION"
    val LATIT_LOCATION = "LATIT_LOCATION"
    val TOTAL_STAY_OVER = "TOTAL_STAY_OVER"
    val CULTURE = "CULTURE"
    val SELECTED_DATE ="SELECTED_DATE"
    val STAY_GUEST_NUMBER = "STAY_GUEST_NUMBER"
    val IMG_STAY = "IMG_STAY"
    val USER_PICTURE = "USER_PICTURE"



    fun init (context: Context) {
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.packageName,
                    Activity.MODE_PRIVATE)
        }
    }

    fun write(key: String?, value: String?){
        apply { sharedPreferences!!.edit().putString(key, value).apply() }
    }

    fun read(key: String?, defaultValue: String?): String?{

        with(sharedPreferences) {
            if (this!!.contains(key)){
                return sharedPreferences!!.getString(key,defaultValue)
            }
        }

        return defaultValue
    }

    fun remove(key: String?){

        if ( sharedPreferences != null && sharedPreferences!!.contains(key)) {
            apply { sharedPreferences?.edit()!!.remove(key).apply() }
        }
    }

    fun removeAll(){
        with(sharedPreferences!!.edit()){
            remove(EMAIL)
            remove(PASSWORD)
            remove(EVENT_NAME)
            remove(HOST_NAME)
            remove(LONG_LOCATION)
            remove(LATIT_LOCATION)
            remove(TOTAL_STAY_OVER)
            remove(SELECTED_DATE)
            remove(STAY_GUEST_NUMBER)
            remove(IMG_STAY)
            remove(USER_PICTURE)
            apply()
        }
    }

}