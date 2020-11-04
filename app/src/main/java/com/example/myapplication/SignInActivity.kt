package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity


class SignInActivity : AppCompatActivity() ,View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }





    fun goToHome(){
        val intentGoToHome = Intent(this,HomeActivity2::class.java)
        startActivity(intentGoToHome)

    }


}






