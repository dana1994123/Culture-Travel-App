package com.example.myapplication.ui.camera_fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btnCameraCapture :ImageButton

    private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


        if (allPermissionGranted()){
            //we have the camera permission

            Log.e(TAG , "granted")
            this.startCamera()
        }
        else{
            //need access
            ActivityCompat.requestPermissions(
                this.requireActivity() ,
                REQUIRED_PERMISSION,
                REQUEST_PERMISSION_CODE
            )

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_camera, container, false)

        this.btnCameraCapture = root.findViewById(R.id.btnCameraCapture)
        this.btnCameraCapture.setOnClickListener(this)

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CameraFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        private const val REQUEST_PERMISSION_CODE = 171
        private const val TAG = "CAMERAX_FRAGMENT"
    }


    private fun allPermissionGranted () = REQUIRED_PERMISSION.all{
        ContextCompat.checkSelfPermission(this.requireContext(),it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQUEST_PERMISSION_CODE){
            if(allPermissionGranted()){

            }
            else{
                Toast.makeText(this.requireContext(), "Operation cannot be performed", Toast.LENGTH_SHORT).show()
                this.findNavController().navigateUp()
            }
        }
    }
    private fun startCamera(){

    }

    override fun onClick(v: View?) {
        if(v!=null){
            when (v.id){
                btnCameraCapture.id ->{

                }
            }
        }
    }
}