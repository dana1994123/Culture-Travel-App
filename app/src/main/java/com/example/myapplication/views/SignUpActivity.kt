package com.example.myapplication.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.models.Guest
import com.example.myapplication.utils.DataValidations
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    val TAG : String = this@SignUpActivity.toString()
    lateinit var signIn : TextView


    companion object{
        var guest = Guest()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btnSignUp.setOnClickListener(this)

        signIn = findViewById(R.id.signInTxt)
        signIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!=null){
            when (v.id){
                btnSignUp.id ->{
                    //validate the data
                    if (this.validateData()){
                        this.fetchData()
                        // save the information in DB
                        this.saveGuestToDB()
                        //navigate to home fragment
                        this.goToHome()
                        //we have to send the guest information to the home
                        // fragment as well using shared prefrence
                    }
                }
                signInTxt.id ->{
                    this.goToSignIn()
                }
            }

        }
    }



    fun goToHome(){
        val intentGoToHome = Intent(this, HomeActivity2::class.java)
        startActivity(intentGoToHome)
        this@SignUpActivity.finishAffinity()
    }




    fun saveGuestToDB(){
        // we have to save the guest data in the cloud
    }


    fun fetchData(){
        //fetch the guest info from the user input
        //we have to add the properety to our guest class to resolve the error
        //guest.name = edtName.text.toString()
        //guest.email = edtEmail.text.toString()
        //guest.password = DataValidations().encryptPassword(edtPassword.text.toString())

    }


    fun validateData() : Boolean{
        //validate alll the data that the user has been entered

        if (edtName.text.toString().isEmpty()){
            edtName.error = "Please enter name"
            return false
        }
        if (edtEmail.text.toString().isEmpty()){
            edtEmail.error = "Email cannot be empty"
            return false
        }else if(!DataValidations().validateEmail(edtEmail.text.toString())){
            edtEmail.error = "Please provide valid email address"
            return false
        }
        if (edtPassword.text.toString().isEmpty()){
            edtPassword.error = getString(R.string.error_empty_password)
            return false
        }

        if (edtConfirmPassword.text.toString().isEmpty()){
            edtConfirmPassword.error = "Confirm password cannot be empty"
            return false
        }

        if (!edtPassword.text.toString().equals(edtConfirmPassword.text.toString())){
            edtPassword.error = "Both passwords must be same"
            edtConfirmPassword.error = "Both passwords must be same"
            return false
        }
        return true
    }



    private fun goToSignIn(){
        //method to navigate back to the sign in activity
        val signInIntent = Intent (this, SignInActivity::class.java)
        startActivity(signInIntent)
    }
}