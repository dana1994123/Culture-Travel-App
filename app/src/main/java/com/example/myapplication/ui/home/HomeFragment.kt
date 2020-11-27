package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var btnReadMe :Button
    private lateinit var btnFirstEvent :Button
    private lateinit var btnSecondEvent :Button
    private lateinit var btnThirdEvent :Button
    private lateinit var btnFourthEvent :Button
    private lateinit var btnFivthEvent :Button
    private lateinit var btnFrench :Button
    private lateinit var btnItaly : Button
    private lateinit var btnIndian :Button





    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        btnReadMe= root.findViewById(R.id.btnReadMore)
        btnFirstEvent = root.findViewById(R.id.btnFirstEvent)
        btnSecondEvent = root.findViewById(R.id.btnSecondEvent)
        btnThirdEvent =  root.findViewById(R.id.btnThirdEvent)
        btnFourthEvent= root.findViewById(R.id.btnFourthEvent)
        btnFivthEvent = root.findViewById(R.id.btnFivthEvent)
        btnFrench = root.findViewById(R.id.frenchStayOverBtn)
        btnItaly = root.findViewById(R.id.italyStayOverBtn)
        btnIndian = root.findViewById(R.id.indianStayOverBtn)


        btnReadMe.setOnClickListener(this)
        btnFivthEvent.setOnClickListener(this)
        btnFourthEvent.setOnClickListener(this)
        btnThirdEvent.setOnClickListener(this)
        btnSecondEvent.setOnClickListener(this)
        btnFirstEvent.setOnClickListener(this)
        btnFrench .setOnClickListener(this)
        btnItaly.setOnClickListener(this)
        btnIndian.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        if (v!=null){
            when(v.id){
                R.id.btnReadMore->{
                    //navigate to about us page
                    findNavController().navigate(R.id.nav_about)
                }
                R.id.btnFirstEvent->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFirstEvent.text.toString())
                    val currentEvent = SharedPreferencesManager.read(SharedPreferencesManager.EVENT_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentEvent.toString() )
                    findNavController().navigate(R.id.eventFragment)
                }
                btnSecondEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtSecondEvent.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnThirdEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtThirdEvent.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnFourthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFourthEvent.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                btnFivthEvent.id->{
                    //save the event name in the shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.EVENT_NAME, txtFivthEvent.toString())
                    findNavController().navigate(R.id.eventFragment)

                }
                italyStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.STAY_OVER_NAME, italyText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }
                frenchStayOverBtn.id->{

                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.STAY_OVER_NAME, frenchText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }
                indianStayOverBtn.id->{
                    //save stayover name in shared prefrence
                    SharedPreferencesManager.write(SharedPreferencesManager.STAY_OVER_NAME, indianText.text.toString())
                    val currentStayOver = SharedPreferencesManager.read(SharedPreferencesManager.STAY_OVER_NAME, "")
                    Log.e ("SHARED PREFRENCES" ,currentStayOver.toString() )
                    findNavController().navigate(R.id.stayOverFragment)

                }


            }
        }
    }


}