package com.example.myapplication.ui.stay_over

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Host
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
class StayOverFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var hostName: String
    private var daysSelected = 0
    lateinit var btnPlus: FloatingActionButton
    lateinit var btnMinus: FloatingActionButton
    lateinit var existingHost: Host
    lateinit var viewModel: ViewModels
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
        btnPlus = root.btnPlus
        btnMinus = root.btnMinus
        root.btnPlus.setOnClickListener(this)
        root.btnMinus.setOnClickListener(this)
        return root
    }

    override fun onResume() {
        tvDaysValue.text = daysSelected.toString()
        viewModel = ViewModels()
        viewModel.fetchStayoverByCulture()
        this.populateStayOver()
//        viewModel.getAllHosts()
//        this.fetchHostDetails()
        super.onResume()
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
        var existingStayOver =StayOverBooking()
    }
    override fun onClick(v: View?) {
            when (v?.id) {
                btnPlus.id -> {
                    if (daysSelected < existingStayOver.maxDuration.toInt()) {
                        tvDaysValue.text = (++daysSelected).toString()
                    }
                }
                btnMinus.id -> {
                    if (daysSelected > 0) {
                        tvDaysValue.text = (--daysSelected).toString()
                    }
                }

            }
    }
    fun populateStayOver(){
        this.viewModel.stayOverList.observe(viewLifecycleOwner,{ stayOver ->
            existingStayOver = stayOver[0]
            SharedPreferencesManager.write(SharedPreferencesManager.HOST_NAME, existingStayOver.hostName)
            if(existingStayOver.culture == "French") {
                tvCulture.text = getString(R.string.french_culture)
            }
            daysSelected = 0
            tvDaysValue.text = daysSelected.toString()
            tvDate.text = "${getString(R.string.from_date)} ${existingStayOver.startDate}"

        })
    }
    fun fetchHostDetails(){
        this.viewModel.hostList.observe(viewLifecycleOwner,{ host->
            existingHost = host[0]
            tvAllowedGuests.text = existingHost.maximumGuests
        })
    }
}