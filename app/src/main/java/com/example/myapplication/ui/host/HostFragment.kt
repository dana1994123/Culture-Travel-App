package com.example.myapplication.ui.host

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Host
import com.example.myapplication.viewmodels.ViewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    val currentHostName = SharedPreferencesManager.read(SharedPreferencesManager.HOST_NAME, "")
    private lateinit var profileImg :ImageView
    private lateinit var edtName : TextView
    private lateinit var edtEmail :TextView
    private lateinit var edtLocation :TextView
    private lateinit var edtAbout :TextView
    private lateinit var viewModel: ViewModels



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel = ViewModels()
        viewModel.getAllHosts()
        this.populateHostProfile()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_host, container, false)
        profileImg = root.findViewById(R.id.profileImg)
        edtName = root.findViewById(R.id.edtName)
        edtEmail = root.findViewById(R.id.edtEmail)
        edtLocation =root.findViewById(R.id.edtLocation)
        edtAbout = root.findViewById(R.id.edtAbout)
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HostFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        var existingHost = Host()
    }
    private fun populateHostProfile() {
        Log.e("host name: ", currentHostName.toString())
            this.viewModel.hostList.observe(this.viewLifecycleOwner, {
                if(it!=null) {
                    existingHost = it[0]
                }
                edtName.setText(currentHostName.toString())
                edtAbout.text = existingHost.about
                edtLocation.text = existingHost.location
                edtEmail.text = existingHost.email
                profileImg.load(existingHost.img)
            })

        }
}