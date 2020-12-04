package com.example.myapplication.ui.stay_over

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
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
    lateinit var viewModel: ViewModels

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
    private lateinit var childrenValue: TextView
    private lateinit var txtCMB: TextView
    private lateinit var txtTotal: TextView
    private lateinit var bookBtn: Button
    private lateinit var mystryCheckBox: CheckBox


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
        txtCMB = root.findViewById(R.id.txtCMB)
        txtTotal = root.findViewById(R.id.txtTotal)
        bookBtn = root.findViewById(R.id.bookBtn)
        mystryCheckBox = root.findViewById(R.id.mystryCheckBox)

        Log.e("current culture" , currentCulture.toString())

       bookBtn.setOnClickListener(this)
        mystryCheckBox.setOnClickListener(this)
        return root
    }


    override fun onResume() {
        super.onResume()
        //tvDaysValue.text = daysSelected.toString()
        viewModel = ViewModels()

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
    }

    fun populateStayOver() {
        this.viewModel.stayOverList.observe(viewLifecycleOwner, { stayOverList ->
            if (stayOverList != null) {
                existingStayOver = stayOverList[0]
                Log.e("current stay Over" , existingStayOver.stayOverName)
            }
            tvCulture.setText(existingStayOver.stayOverName.toString())
            //fetch the image from the Db


            edtHostName.setText(existingStayOver.host.name)
            //fetch the host image

        })
    }

    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                bookBtn.id ->{
                    //create an object of the stayOverBooking and save it in the Shared preference so
                    // we can save it in the payment fragment once the user confirm the payment

                }
                txtCMB.id ->{
                    //show alert box have some information about it
                }

            }
        }
    }


//            if(existingStayOver.culture == "French") {
//                tvCulture.text = getString(R.string.french_culture)
//            }
//            daysSelected = 0
//            tvDaysValue.text = daysSelected.toString()
//            tvDate.text = "${getString(R.string.from_date)} ${existingStayOver.startDate}"
//
//        })

//    fun fetchHostDetails(){
//        this.viewModel.hostList.observe(viewLifecycleOwner,{ host->
//            exisitingHost = host[0]
//            tvAllowedGuests.text = exisitingHost.maximumGuests
//        })
//    }

}
