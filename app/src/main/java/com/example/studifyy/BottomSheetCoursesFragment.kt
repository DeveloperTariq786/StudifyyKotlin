package com.example.studifyy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.studifyy.databinding.FragmentBottomSheetCoursesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth

class BottomSheetCoursesFragment:BottomSheetDialogFragment() {
    private var selectedProgram: String? = null
    private lateinit var binding:FragmentBottomSheetCoursesBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences=requireActivity().getSharedPreferences("OfflineData", Context.MODE_PRIVATE)
        selectedProgram=sharedPreferences.getString("Store",null)
        auth=FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBottomSheetCoursesBinding.inflate(inflater,container,false)
        binding.DataProgramSaved.text=selectedProgram
       binding.Logout.setOnClickListener {
           auth.signOut()
           val intent=Intent(context,RegistrationActivity::class.java)
           startActivity(intent)
       }
        binding.Share.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                val appLink = "https://play.google.com/store/apps/details?id=com.example.studifyy"
                val message = "Check out this awesome app:\n$appLink"
                shareIntent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
           binding.expandableTextView.setOnClickListener {
            }
        }
       return binding.root
    }


}
