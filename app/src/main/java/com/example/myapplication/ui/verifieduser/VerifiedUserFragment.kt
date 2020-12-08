package com.example.myapplication.ui.verifieduser

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.myapplication.R
import com.example.myapplication.managers.SharedPreferencesManager
import com.example.myapplication.models.Guest
import com.example.myapplication.ui.profile.ProfileFragment
import com.example.myapplication.viewmodels.ViewModels
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.verified_user.*


class VerifiedUserFragment : Fragment() , View.OnClickListener{
    private lateinit var verifiedBtn : Button

    private lateinit var verifiedUserViewModel: VerifiedUserViewModel
    private lateinit var cameraFragment : Fragment
    private val REQUEST_GALLERY_PICTURE=172
    private val currentUser = SharedPreferencesManager.read(SharedPreferencesManager.EMAIL, "").toString()
    private lateinit var imgId :ImageView
    private lateinit var viewModel : ViewModels


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        verifiedUserViewModel =
                ViewModelProvider(this).get(VerifiedUserViewModel::class.java)
        val root = inflater.inflate(R.layout.verified_user, container, false)
        verifiedBtn = root.findViewById(R.id.verifiedBtn)
        verifiedBtn.setOnClickListener(this)
        imgId = root.findViewById(R.id.imgId)
        viewModel = ViewModels()
        viewModel.fetchAllGuest()
        this.getUserInformation()


        return root
    }

    companion object{
        var existingGuest = Guest()
    }
    override fun onClick(v: View?) {
        if(v !=null){
            var fg : Fragment;
            when (v.id){
                verifiedBtn.id -> {
                    if(existingGuest.verifiedImage == ""){
                        //take a picture change the verification status for each user and then show the verification sign
                        val actionItems = arrayOf("Choose from Gallery", "Cancel")
                        var alertDialog = AlertDialog.Builder(this.requireContext())
                        alertDialog.setTitle("Become Verified User")
                        alertDialog.setItems(actionItems) { dialog, index ->
                            if (actionItems.get(index).equals("Choose from Gallery")) {
                                this.selectFromGallery()


                            } else if (actionItems.get(index).equals("Cancel")) {
                                dialog.dismiss()
                            }
                        }
                        alertDialog.show()

                    }
                    else{
                        Toast.makeText(this.requireActivity() , "You have already submit your Id will get back to you ASAP" , Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }


    }

    override fun onResume() {
        super.onResume()


    }

    private fun getUserInformation(){
        this.viewModel.guestList.observe(this.requireActivity(), { matchedGuest ->
            if (matchedGuest != null) {
                existingGuest = matchedGuest[0]
            }
            imgId.load(existingGuest.verifiedImage)
        })
    }


    private fun selectFromGallery(){
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == this.REQUEST_GALLERY_PICTURE){
                val alertDialog = AlertDialog.Builder(this.requireActivity())
                alertDialog.setTitle("Thank you!")
                alertDialog.setMessage("You have Submit successfully your id. ")
                this.imgId.setImageURI(data?.data)

                // save the id iamge in the db
                existingGuest.verifiedImage =  data?.data.toString()
                viewModel.updateGuest2(existingGuest)
                Log.e("image verification" ,existingGuest.verifiedImage.toString() )


                alertDialog.setNegativeButton(android.R.string.cancel){ dialog, which ->
                    Toast.makeText(
                        this.requireActivity(),
                        "Our team will get back to you soon!",
                        Toast.LENGTH_LONG
                    ).show()
                }

                alertDialog.show()



            }
        }
    }
}