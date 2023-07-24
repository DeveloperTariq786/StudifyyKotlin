package com.example.studifyy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.studifyy.databinding.FragmentBottomSheetBMPBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetBMPFragment : DialogFragment() {
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
    ): View? {

        return inflater.inflate(R.layout.fragment_bottom_sheet_b_m_p, container, false)
    }

}
