package com.example.studifyy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studifyy.databinding.FragmentBottomSheetCoursesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetCoursesFragment:BottomSheetDialogFragment() {
    private var selectedProgram: String? = null
    private lateinit var binding:FragmentBottomSheetCoursesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences=requireActivity().getSharedPreferences("OfflineData", Context.MODE_PRIVATE)
        selectedProgram=sharedPreferences.getString("Store",null)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBottomSheetCoursesBinding.inflate(inflater,container,false)
        binding.DataProgramSaved.text=selectedProgram
       return binding.root
    }
}
