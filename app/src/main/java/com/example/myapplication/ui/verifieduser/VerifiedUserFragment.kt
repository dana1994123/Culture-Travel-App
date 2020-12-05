package com.example.myapplication.ui.verifieduser

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class VerifiedUserFragment : Fragment() , View.OnClickListener{
    private lateinit var verifiedBtn : Button

    private lateinit var verifiedUserViewModel: VerifiedUserViewModel

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
        return root
    }

    override fun onClick(v: View?) {
        if(v !=null){
            when (v.id){
                verifiedBtn.id -> {
                    //take a picture change the verification status for each user and then show the verification sign
                    val actionItems = arrayOf("Scan your Id", "Choose from Gallery", "Cancel")
                    var alertDialog = AlertDialog.Builder(this.requireContext())
                    alertDialog.setTitle("Become Verified User")
                    alertDialog.setItems(actionItems) { dialog, index ->
                        if (actionItems.get(index).equals("Scan your Id")) {


                        } else if (actionItems.get(index).equals("Choose from Gallery")) {

                        } else if (actionItems.get(index).equals("Cancel")) {
                            dialog.dismiss()
                        }
                    }
                    alertDialog.show()

                }
            }
        }
    }
}