package com.example.myapplication.managers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {


    private var sharedPreferences: SharedPreferences? = null

    val EMAIL = "KEY_EMAIL"
    val PASSWORD = "KEY_PASSWORD"
    val EVENT_NAME = "KEY_EVENT"
    var HOST_NAME = "HOST_NAME"
    var CULTURE = "CULTURE"
    var LONG_LOCATION = "LONG_LOCATION"
    var LATIT_LOCATION = "LATIT_LOCATION"


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
            apply()
        }
    }

}