package com.example.myapplication.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.locationmanager.LocationManager
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.utils.DataValidations
import com.example.myapplication.viewmodels.ViewModels
import kotlinx.android.synthetic.main.activity_sign_in.*



class SignInActivity : AppCompatActivity() ,View.OnClickListener {

    val TAG = this@SignInActivity.toString()
    lateinit var tvCreateAccount: TextView


    lateinit var viewModels: ViewModels


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)






        tvCreateAccount = findViewById(R.id.createAccount)
        tvCreateAccount.setOnClickListener(this)

        loginBtn.setOnClickListener(this)



        SharedPreferencesManager.init(applicationContext)
        this.fetchPreferences()
    }

    override fun onResume() {
        super.onResume()
        viewModels = ViewModels()
        viewModels.fetchAllGuest()
        Log.e("resume working" , "resume")

    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                loginBtn.id -> {
                    if (this.validateData()) {
                        SharedPreferencesManager.write(SharedPreferencesManager.EMAIL, edtEmail.text.toString())
                        this.validateUser()

                    }
                }
                tvCreateAccount.id -> {
                    this.goToSignUp()
                }
            }
        }
    }


    private fun validateUser() {
        // we have to validate the information that the user has been input by
        // searching our database
        val email = edtEmail.text.toString()
        val password = DataValidations().encryptPassword(edtPassword.text.toString())

        viewModels.guestList.observe(this,{matchedGuestList ->
            if(matchedGuestList != null){
                if(matchedGuestList[0].password == password){
                    //correct credential
                    Log.e("correct current Guest",SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString() )
                    this.checkRemember()
                    this@SignInActivity.finishAndRemoveTask()
                    this.goToHome()

                }
                else{
                    Toast.makeText(this, "Incorrect Login/Password. Try again!", Toast.LENGTH_LONG).show()
                }
            }
        })







    }




    private fun validateData() : Boolean {


        if (edtEmail.text.isEmpty()&& edtPassword.text.isEmpty()){
            edtPassword.setError("Password cannot be empty")
            edtEmail.setError("Email cannot be empty")
            return false
        }
        if (edtEmail.text.isEmpty()){
            edtEmail.setError("Email cannot be empty")
            return false
        }

        if (edtPassword.text.isEmpty()){
            edtPassword.setError("Password cannot be empty")
            return false
        }



        if (!DataValidations().validateEmail(edtEmail.text.toString())){
            edtEmail.setError("Please provide valid email address")
            return false
        }

        return true
    }

    fun goToHome(){
        val intentGoToHome = Intent(this, HomeActivity2::class.java)
        startActivity(intentGoToHome)
        this@SignInActivity.finishAffinity()

    }



    private fun checkRemember(){
        SharedPreferencesManager.write(SharedPreferencesManager.EMAIL, edtEmail.text.toString())
        if (swtRemember.isChecked){
            //save the credentials in shared preferences
            SharedPreferencesManager.write(SharedPreferencesManager.PASSWORD, edtPassword.text.toString())
        }else{
            //remove the credentials from shared preferences
            SharedPreferencesManager.remove(SharedPreferencesManager.PASSWORD)
        }
    }




    private fun fetchPreferences(){
        edtEmail.setText(SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, ""))
        edtPassword.setText(SharedPreferencesManager.read(SharedPreferencesManager.PASSWORD,""))
    }



    private fun goToSignUp(){
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }







}






