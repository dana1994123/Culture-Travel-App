package com.example.myapplication.ui.stay_over

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.communication.Communicator
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.BookingEvent
import com.example.myapplication.models.Host
import com.example.myapplication.models.StayOver
import com.example.myapplication.models.StayOverBooking
import com.example.myapplication.viewmodels.ViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_stay_over.*
import kotlinx.android.synthetic.main.fragment_stay_over.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var chosenCulture = SharedPreferencesManager.read(SharedPreferencesManager.CULTURE,"").toString()
/**
 * A simple [Fragment] subclass.
 * Use the [StayOverFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StayOverFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var hostName: String
    private var daysSelected = 0
    private var selectedAdults = 0
    private var selectedChildren = 0
    private var totalPay = 0
    lateinit var viewModel: ViewModels
    private var selectedDate =""
    private var isChecked = 0

    val currentCulture = SharedPreferencesManager.read(SharedPreferencesManager.CULTURE, "")



    //UI Elements
    private lateinit var tvCulture: TextView
    private lateinit var stayOverImg3: ImageView
    private lateinit var stayOverImg2: ImageView
    private lateinit var stayOverImg1: ImageView
    private lateinit var edtHostName: TextView
    private lateinit var hostImg: ImageView
    private lateinit var adultValue: TextView
    private lateinit var adultMinusBtn: FloatingActionButton
    private lateinit var adultPlusBtn: FloatingActionButton
    private lateinit var childMinusBtn: FloatingActionButton
    private lateinit var childPlusBtn: FloatingActionButton
    private lateinit var btnPlus: FloatingActionButton
    private lateinit var btnMinus: FloatingActionButton
    private lateinit var childrenValue: TextView
    private lateinit var txtCMB: TextView
    private lateinit var txtTotal: TextView
    private lateinit var bookBtn: Button
    private lateinit var mystryCheckBox: CheckBox
    private lateinit var selectedRadioButton: RadioButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_stay_over, container, false)




        tvCulture = root.findViewById(R.id.tvCulture)
        stayOverImg3 = root.findViewById(R.id.stayOverImg3)
        stayOverImg2 = root.findViewById(R.id.stayOverImg2)
        stayOverImg1 = root.findViewById(R.id.stayOverImg1)
        edtHostName = root.findViewById((R.id.edtHostName))
        hostImg = root.findViewById(R.id.hostImg)
        adultValue = root.findViewById(R.id.adultValue)
        adultMinusBtn = root.findViewById(R.id.adultMinusBtn)
        adultPlusBtn = root.findViewById(R.id.adultPlusBtn)
        childMinusBtn = root.findViewById(R.id.childMinusBtn)
        childPlusBtn = root.findViewById(R.id.childPlusBtn)
        childrenValue = root.findViewById(R.id.childrenValue)
        btnMinus = root.findViewById(R.id.btnDurationMinus)
        btnPlus = root.findViewById(R.id.btnDurationPlus)
        txtCMB = root.findViewById(R.id.txtCMB)
        txtTotal = root.findViewById(R.id.txtTotal)
        bookBtn = root.findViewById(R.id.bookBtn)
        mystryCheckBox = root.findViewById(R.id.mystryCheckBox)



        Log.e("current culture" , currentCulture.toString())

        bookBtn.setOnClickListener(this)
        mystryCheckBox.setOnClickListener(this)
        btnPlus.setOnClickListener(this)
        btnMinus.setOnClickListener(this)
        adultPlusBtn.setOnClickListener(this)
        adultMinusBtn.setOnClickListener(this)
        childMinusBtn.setOnClickListener(this)
        childPlusBtn.setOnClickListener(this)
        txtCMB.setOnClickListener(this)
        hostImg.setOnClickListener(this)
        return root
    }


    override fun onResume() {
        super.onResume()
        //tvDaysValue.text = daysSelected.toString()
        viewModel = ViewModels()
        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"
        viewModel.fetchStayoverByCulture()


        this.populateStayOver()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StayOverFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StayOverFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        var existingStayOver = StayOver()
        var stayBooking = StayOverBooking()
    }

    fun populateStayOver() {
        this.viewModel.stayOverList.observe(viewLifecycleOwner, { stayOverList ->
            if (stayOverList != null) {
                existingStayOver = stayOverList[0]
                //fetch the image from the Db
                stayOverImg1.load(existingStayOver.img1)
                stayOverImg2.load(existingStayOver.img2)
                stayOverImg3.load(existingStayOver.img3)
                //fetch the host image
                hostImg.load(existingStayOver.host.img)
                Log.e("current stay Over" , existingStayOver.stayOverName)
            }
            dateRb1.text = existingStayOver.dates[0]
            dateRb2.text = existingStayOver.dates[1]
            dateRb3.text = existingStayOver.dates[2]
            //save the host name to use it the host fragment
            SharedPreferencesManager.write(SharedPreferencesManager.HOST_NAME, existingStayOver.host.name)

            tvCulture.setText(existingStayOver.stayOverName.toString())



            edtHostName.setText(existingStayOver.host.name)


        })
    }
    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                mystryCheckBox.id-> {
                    if (mystryCheckBox.isChecked) {

                       isChecked = 30
                    }
                        else{
                            isChecked = 0
                        }
                    totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                    txtTotal.text = "${getString(R.string.total_233)}${totalPay}"
                }
                bookBtn.id ->{
                    //create an object of the stayOverBooking and save it in the Shared preference so
                    // we can save it in the payment fragment once the user confirm the payment
                    if(totalPay != 0 && rdgDates.checkedRadioButtonId != -1){
                        selectedRadioButton = requireView().findViewById(rdgDates.checkedRadioButtonId)
                        selectedDate = selectedRadioButton.text.toString()
                        //pass the total to the stay over fragment

                        SharedPreferencesManager.write(SharedPreferencesManager.IMG_STAY,existingStayOver.img1)
                        SharedPreferencesManager.write(SharedPreferencesManager.TOTAL_STAY_OVER,totalPay.toString())
                        SharedPreferencesManager.write(SharedPreferencesManager.SELECTED_DATE,selectedDate)
                        SharedPreferencesManager.write(SharedPreferencesManager.STAY_GUEST_NUMBER,"Adult : ${selectedAdults} , Child: ${selectedChildren}")

                        findNavController().navigate(R.id.paymentFragment)
                    }
                    else{
                        Toast.makeText(this.requireContext() , "Please Select your reservation's option first" , Toast.LENGTH_SHORT).show()
                    }


                }
                txtCMB.id ->{
                    //show alert box have some information about it
                    val alertBuilder = AlertDialog.Builder(requireContext())
                    alertBuilder.setTitle(getString(R.string.mystery_box_title))
                    alertBuilder.setMessage(getString(R.string.mystery_box_content))
                    alertBuilder.setPositiveButton(android.R.string.yes){dialog,which ->
                    }
                    alertBuilder.show()
                }
                btnPlus.id ->{
                    if(daysSelected < existingStayOver.maxDuration.toInt()){
                        tvDurationValue.text = (++daysSelected).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"
                    }
                }
                btnMinus.id->{
                    if(daysSelected > 0){
                        tvDurationValue.text = (--daysSelected).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"
                    }
                }
                adultPlusBtn.id->{
                    if(selectedAdults < existingStayOver.maxAdult.toInt()){
                        adultValue.text = (++selectedAdults).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"

                    }
                }
                adultMinusBtn.id->{
                    if(selectedAdults > 0){
                        adultValue.text = (--selectedAdults).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"

                    }
                }
                childMinusBtn.id->{
                    if(selectedChildren > 0){
                        childrenValue.text = (--selectedChildren).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"

                    }
                }
                childPlusBtn.id->{
                    if(selectedChildren < existingStayOver.maxChild.toInt()){
                        childrenValue.text = (++selectedChildren).toString()
                        totalPay = this.calculateTotal(selectedChildren,selectedAdults,daysSelected,isChecked)
                        txtTotal.text = "${getString(R.string.total_233)}${totalPay}"

                    }
                }
                hostImg.id->{
                    findNavController().navigate(R.id.hostFragment)
                }
            }
        }

    }

    private fun calculateTotal(children:Int,adults:Int,days:Int,isChecked: Int):Int{
            if(days>0 && (adults>0 || children > 0)) return (((children+adults+days)*60)+isChecked)
            else return 0
    }





}
