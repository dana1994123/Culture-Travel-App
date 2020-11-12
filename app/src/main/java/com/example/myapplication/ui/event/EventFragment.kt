package com.example.myapplication.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_event.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNSAFE_CALL_ON_PARTIALLY_DEFINED_RESOURCE")
class EventFragment : Fragment() , View.OnClickListener{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var edtFirstImage : ImageView
    lateinit var edtSecondImage :ImageView
    lateinit var edtEventName :TextView
    lateinit var edtEventLocation :TextView
    lateinit var edtEventHost :TextView
    lateinit var edtEventDuration :TextView
    lateinit var edtLang :TextView
    lateinit var edtDate : TextView
    lateinit var edtEventDesc :TextView
    lateinit var btnEventPrice :Button


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
        val root =inflater.inflate(R.layout.fragment_event, container, false)
        edtFirstImage = root.edtFirstImage
        edtSecondImage = root.edtSecondImage
        edtEventName = root.edtEventName
        edtEventLocation = root.edtEventLocation
        edtEventHost = root.edtEventHost
        edtEventDuration = root.edtEventDuration
        edtLang = root.edtLang
        edtDate = root.edtDate
        edtEventDesc = root.edtEventDesc
        btnEventPrice = root.btnEventPrice

        //create a method to populate the event by fetching the event to the fragment
        this.populateEvent ()

        root.btnEventPrice.setOnClickListener(this)

        return  root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun populateEvent(){
        //create a method to populate the event by fetching the event to the fragment
    }
    private fun goToPayment(){
        //navigate to the payment page and send the event price along
    }
    override fun onClick(v: View?) {
        if (v!=null ){
            when(v.id){
                btnEventPrice.id->{
                    //navigate to the payment page and send the event price along
                    this.goToPayment()
                }
            }
        }
    }
}