package com.example.studifyy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studifyy.databinding.FragmentBottomSheetBMPBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetBMPFragment:BottomSheetDialogFragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentBottomSheetBMPBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBottomSheetBMPBinding.inflate(inflater,container,false)
        binding.Notes.setOnClickListener {
            val intent=Intent(context,MainActivity::class.java)
            startActivity(intent)
        }
       return binding.root
    }
}
