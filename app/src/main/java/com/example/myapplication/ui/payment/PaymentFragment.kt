package com.example.myapplication.ui.payment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.views.HomeActivity2
import com.example.myapplication.views.SignInActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_payment.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var edtName: EditText
    lateinit var edtCarPlateNumber: EditText
    lateinit var edtPhoneNumber: EditText
    lateinit var edtCreditCardNum: EditText
    lateinit var edtCvv: EditText
    lateinit var edtExpiry: EditText
    lateinit var edtNameOnCard: EditText
    lateinit var btnPayment: Button
    lateinit var fabEditProfile: FloatingActionButton


    var currentUserEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_payment, container, false)

        root.btnPayment.setOnClickListener(this)
        edtName = root.edtName
        edtNameOnCard = root.edtNameOnCard
        edtExpiry = root.edtExpiry
        edtCreditCardNum = root.edtCreditCardNum
        edtCvv = root.edtCvv
        edtPhoneNumber = root.edtPhoneNumber

        btnPayment = root.btnPayment



        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            btnPayment.id ->{
                //we have to check the validity of the entered information
                if (this.validateDate()){
                    this.saveTripToDb()
                    //navigate to the home page
                    this.goToHome()

                }
            }
        }
    }


    private fun validateDate():Boolean{
        //put payment validation :
        return true
    }



    private fun saveTripToDb(){
        // if the payment is correct we have to save the trip in guest history
    }


    fun goToHome(){
        val intentGoToHome = Intent(getActivity(), HomeActivity2::class.java)
        getActivity()?.startActivity(intentGoToHome)



    }

}