package com.example.myapplication.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import com.example.myapplication.locationmanager.LocationManager
import com.example.myapplication.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.edtEmail

class HomeActivity2 : AppCompatActivity(), View.OnClickListener {

    private val TAG = this@HomeActivity2.toString()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
//    private val currentUser = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
    private lateinit var locationManager :LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)




        locationManager = LocationManager(this@HomeActivity2)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_about, R.id.nav_trip_history,R.id.nav_contact,R.id.nav_verified_user), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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

    override fun onResume() {
        super.onResume()
        btnReadMore.setOnClickListener(this)
        btnFirstEvent.setOnClickListener(this)
        btnSecondEvent.setOnClickListener(this)
        btnThirdEvent.setOnClickListener(this)
        btnFourthEvent.setOnClickListener(this)
        btnFivthEvent.setOnClickListener(this)
        italyStayOverBtn.setOnClickListener(this)
        frenchStayOverBtn.setOnClickListener(this)
        indianStayOverBtn.setOnClickListener(this)
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
        if (v!=null){
            when(v.id){
                btnReadMore.id ->{
                    //navigate to about us page
                    navController.navigate(R.id.nav_about)
                }
                btnFirstEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFirstEvent.text.toString())
                    val currentEvent = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentEvent.toString() )
                    navController.navigate(R.id.eventFragment)
                }
                btnSecondEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtSecondEvent.toString())
                    navController.navigate(R.id.eventFragment)

                }
                btnThirdEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtThirdEvent.toString())
                    navController.navigate(R.id.eventFragment)

                }
                btnFourthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFourthEvent.toString())
                    navController.navigate(R.id.eventFragment)

                }
                btnFivthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFivthEvent.toString())
                    navController.navigate(R.id.eventFragment)

                }
                italyStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFirstEvent.text.toString())
                    val currentEvent = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentEvent.toString() )
                    navController.navigate(R.id.stayOverFragment)

                }
                frenchStayOverBtn.id->{

                    //save stayover name in shared prefrence
                    navController.navigate(R.id.stayOverFragment)

                }
                indianStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    navController.navigate(R.id.stayOverFragment)

                }


            }
        }
    }

}