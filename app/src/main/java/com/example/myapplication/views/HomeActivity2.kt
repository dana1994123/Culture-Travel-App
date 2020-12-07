package com.example.myapplication.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.example.myapplication.R
//import com.example.myapplication.LocationManager
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Guest
import com.example.myapplication.viewmodels.ViewModels
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.edtEmail

class HomeActivity2 : AppCompatActivity(),View.OnClickListener{

    private val TAG = this@HomeActivity2.toString()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var name : TextView
    private lateinit var email: TextView
    private lateinit var imgProfilePic: ImageView
    private val REQUEST_GALLERY_PICTURE=172
    private lateinit var viewModel : ViewModels
    private lateinit var  currentUser : Guest



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel = ViewModels()
        viewModel.fetchAllGuest()

        drawerLayout= findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_about, R.id.nav_trip_history,R.id.nav_contact,
            R.id.nav_verified_user,R.id.eventFragment,R.id.stayOverFragment,R.id.action_viewProfile,R.id.fragment_camera), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val headerLayout: View = navView.getHeaderView(0)
        email= headerLayout.findViewById(R.id.edtEmail)
        name= headerLayout.findViewById(R.id.edtUserName)
        imgProfilePic=headerLayout.findViewById(R.id.imgProfilePic)
        imgProfilePic.setOnClickListener(this)

        this.getUserInfo()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_activity2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_signOut ->{
                this.finishAffinity()
                val signInIntent = Intent(this, SignInActivity::class.java)
                startActivity(signInIntent)
            }
            R.id.action_viewProfile->{
                //we have to open a profile fragment using the nav controller
                navController.navigate(R.id.nav_profile)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if(v != null){
            when(v.id){
                R.id.imgProfilePic -> {
                    val actionItems= arrayOf("Take a New Picture","Choose from Gallery","Cancel")
                    val alertBuilder= AlertDialog.Builder(this)
                    alertBuilder.setTitle("Select Profile Picture")
                    alertBuilder.setItems(actionItems){dialog, index->
                        if(actionItems.get(index).equals("Take a New Picture")){
                            Log.d("HomeActivity","Taking new Picture")
                            navController.navigate(R.id.action_nav_home_to_camera_fragment)
                            this.drawerLayout.closeDrawer(Gravity.LEFT,true)


                        }else if(actionItems.get(index).equals("Choose from Gallery")){
                            Toast.makeText(this,"Choosing from gallery", Toast.LENGTH_SHORT).show()
                            this.selectFromGallery()


                        }else if(actionItems.get(index).equals("Cancel")){
                            dialog.dismiss()
                        }

                    }
                    alertBuilder.show()
                }
            }
        }
    }

    private fun selectFromGallery(){
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == this.REQUEST_GALLERY_PICTURE){
                this.imgProfilePic.setImageURI(data?.data)
            }
        }
    }


    fun getUserInfo(){
        viewModel.guestList.observe(this,{
            if(it != null){
                currentUser = it[0]
                edtUserName.setText(currentUser.name)
                edtEmail.setText(currentUser.email)
            }
        })
    }



}