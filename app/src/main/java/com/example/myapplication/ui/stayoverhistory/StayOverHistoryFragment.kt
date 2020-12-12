package com.example.myapplication.ui.stayoverhistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.StayBookingAdapter
import com.example.myapplication.models.StayOverBooking
import com.example.myapplication.viewmodels.ViewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StayOverHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StayOverHistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var viewModel : ViewModels
    private lateinit var stayOverList: MutableList<StayOverBooking>
    private lateinit var viewStayAdapter: StayBookingAdapter
    private lateinit var rvBooking : RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



        viewModel = ViewModels()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_stay_over_history, container, false)
        this.rvBooking = root.findViewById(R.id.rvBookingStay)
        stayOverList = mutableListOf()
        this.viewStayAdapter = StayBookingAdapter(this.requireContext(),stayOverList)
        this.rvBooking.adapter = this.viewStayAdapter


        this.viewManager = LinearLayoutManager(this.requireContext())
        this.rvBooking.layoutManager = this.viewManager

        this.rvBooking.setHasFixedSize(true)
        this.rvBooking.addItemDecoration(DividerItemDecoration(this.requireContext(), DividerItemDecoration.VERTICAL))

        return  root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StayOverHistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StayOverHistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    override fun onResume() {
        super.onResume()

        viewModel.getBookingStayOver()

        this.getBookingStayOver()
        Log.e("current list of saty over " , stayOverList.toString())
    }



    fun getBookingStayOver(){
        this.viewModel.stayOverBookinList.observe(viewLifecycleOwner, {stayBookingsList ->
            if (stayBookingsList != null){
                Log.e("current second staylist " , stayBookingsList.toString())
                stayOverList.clear()
                stayOverList.addAll(stayBookingsList)
                viewStayAdapter.notifyDataSetChanged()
            }
        })
    }



}