package com.example.myapplication.utils

import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class DataValidations {



    fun validateEmail(email: String) : Boolean{
        return !TextUtils.isEmpty(email) &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun validatePhonenNum(phone:String):Boolean{
        return !TextUtils.isEmpty(phone) && phone.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}\$\n".toRegex())
    }
    fun validateName(name:String):Boolean{
        return !TextUtils.isEmpty(name) && name.matches("[a-zA-Z]".toRegex())
    }
    fun validateExpiryDate(expiryDate:String):Boolean{
        return !TextUtils.isEmpty(expiryDate) &&
                expiryDate.matches("^/^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})\$/".toRegex())
    }
    fun encryptPassword(password: String) : String{
        try{
            val md = MessageDigest.getInstance("SHA-256")
            md.update(password.toByteArray())

            val hashPass = md.digest()

            return Base64.encodeToString(hashPass, Base64.DEFAULT)

        }catch(ex: NoSuchAlgorithmException){
            Log.e("Data Validations", ex.localizedMessage)
        }

        return ""
    }




}