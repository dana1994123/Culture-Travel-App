package com.example.myapplication.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Guest
import com.example.myapplication.viewmodels.ViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_sign_up.view.edtName
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.lang.Exception

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
    lateinit var edtEmail :EditText
    lateinit var btnSave: Button
    lateinit var spnLang :Spinner
    lateinit var fabEditProfile: FloatingActionButton

    var selectedLang: String = ""
    var currentGuestrEmail = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "")
    lateinit var viewModel :ViewModels





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
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        root.fabEditProfile.setOnClickListener(this)
        root.btnSave.setOnClickListener(this)
        edtName = root.edtName
        edtEmail = root.edtEmail
        edtPhoneNumber = root.edtPhoneNumber
        btnSave = root.btnSave
        spnLang = root.spnLang
        fabEditProfile = root.fabEditProfile
        this.disableEdit()
        this.initializeSpinner()
        selectedLang = resources.getStringArray(R.array.language_array).get(0)
        spnLang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedLang = resources.getStringArray(R.array.language_array).get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedLang = resources.getStringArray(R.array.language_array).get(0)
            }
        }
        return  root
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
        var existingGuest = Guest()

    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModels()
        viewModel.fetchAllGuest()
        this.populateProfile()

    }
    fun populateProfile() {
        //create a method to fetch the guest information from the DB
        // and populate it in the ui
        this.viewModel.guestList.observe(this.requireActivity(), { matchedGuest ->
            if (matchedGuest != null) {
                existingGuest = matchedGuest[0]
            }
            edtEmail.setText(existingGuest.email)
            edtName.setText(existingGuest.name)
            edtPhoneNumber.setText(existingGuest.phoneNumber)
        })



        Log.e("userphone number" , existingGuest.phoneNumber.toString())
    }



    fun initializeSpinner(){
        val langAdapter = ArrayAdapter(requireActivity(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.language_array))
        spnLang.adapter = langAdapter
    }








    fun disableEdit(){
        edtName.isEnabled = false
        edtEmail.isEnabled = false
        edtPhoneNumber.isEnabled = false
        spnLang.isEnabled = false
    }

    fun enableEdit(){
        edtName.isEnabled = true
        spnLang.isEnabled = true
        edtPhoneNumber.isEnabled = true
    }



    override fun onClick(v: View?) {
        when(v?.id){
            fabEditProfile.id -> {
                this.enableEdit()
                fabEditProfile.visibility = View.GONE
                btnSave.visibility = View.VISIBLE
                Log.e("before guest" , existingGuest.toString())
            }
            btnSave.id -> {
                this.disableEdit()
                fabEditProfile.visibility = View.VISIBLE
                btnSave.visibility = View.GONE
                this.saveToDB()
            }
        }
    }


    private fun saveToDB(){
        //WE have to fetch the data that the user has been
        // change and update his profile on the DB
        existingGuest.phoneNumber = edtPhoneNumber.text.toString()
        existingGuest.name = edtName.text.toString()
        existingGuest.language = spnLang.selectedItem.toString()
        viewModel.updateGuest2(existingGuest)
        Log.e("the current user" , existingGuest.toString())
    }
}