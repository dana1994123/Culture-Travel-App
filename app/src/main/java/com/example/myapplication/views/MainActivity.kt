package com.example.myapplication.views

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() , View.OnClickListener {


    private lateinit var timer: CountDownTimer
    private lateinit var  signInIntent :Intent






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timer = MyCounter(1000, 30000)
        timer.start()
        signInIntent = Intent(this, SignInActivity::class.java)
        imageView.setOnClickListener(this)
        startTxT.setOnClickListener(this)


    }

    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        override fun onFinish() {
            //val signIn = Intent(this, SignInActivity::class.java)
            startTxT.text = "Click to Continue!"
        }

        override fun onTick(millisUntilFinished: Long) {
            Log.e("timer","loading")
        }
    }

    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                startTxT.id->{
                    startActivity(signInIntent)
                }
                imageView.id->{
                    startActivity(signInIntent)
                }
            }
        }
    }


}