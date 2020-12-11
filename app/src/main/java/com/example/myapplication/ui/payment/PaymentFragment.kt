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
import com.example.myapplication.models.Payment
import com.example.myapplication.utils.DataValidations
import com.example.myapplication.viewmodels.PaymentViewModel
import com.example.myapplication.viewmodels.ViewModels
import com.example.myapplication.views.HomeActivity2
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
    lateinit var edtPhoneNumber: EditText
    lateinit var edtCreditCardNum: EditText
    lateinit var edtCvv: EditText
    lateinit var edtExpiry: EditText
    lateinit var edtNameOnCard: EditText
    lateinit var btnPayment: Button
    lateinit var fabEditProfile: FloatingActionButton


    var currentUserEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "")

    private lateinit var paymentViewModel : PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        paymentViewModel = PaymentViewModel()
        newPayment.email = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL,"").toString()
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
        var newPayment = Payment()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            btnPayment.id ->{
                //we have to check the validity of the entered information
                if (this.validateDate()){
                    this.saveTripToDb()
                    this.savePurchaseToDB()
                    //navigate to the home page
                    this.goToHome()

                }
            }
        }
    }


    private fun validateDate():Boolean{
        //put payment validation :
        if(DataValidations().validateName(edtName.text.toString())){
            edtName.error = "Please enter a valid name"
            return false
        }
        if(DataValidations().validatePhonenNum(edtPhoneNumber.text.toString())){
            edtPhoneNumber.error = "Please provide a valid phone number"
            return false
        }
        if(edtCreditCardNum.text.toString().isEmpty() || edtCreditCardNum.text.toString().length<15){
            edtCreditCardNum.error = "Please enter a valid credit card number"
            return false
        }
        if(DataValidations().validateName(edtNameOnCard.text.toString())){
            edtNameOnCard.error = "Please provide a valid name that belongs to the credit card"
            return false
        }
        if(edtCvv.text.toString().isEmpty()){
            edtCvv.error = "Please provide the credit card's cvv"
            return false
        }
        if(DataValidations().validateExpiryDate(edtExpiry.text.toString())){
            edtExpiry.error = "Please add the expiry date"
            return false
        }

        return true
    }

    private fun savePurchaseToDB(){
        //adds the purchase to the database
        paymentViewModel.addPayment(newPayment)

    }

    private fun saveTripToDb(){

        // if the payment is correct we have to save the trip in guest history
    }


    fun goToHome(){
        val intentGoToHome = Intent(getActivity(), HomeActivity2::class.java)
        getActivity()?.startActivity(intentGoToHome)



    }

}