package com.example.studifyy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.studifyy.databinding.AllcourcesrecyclerviewlayoutBinding

class CoursesRecyclerViewAdapter(private var list:ArrayList<CoursesModel>,private var context:Context): RecyclerView.Adapter<CoursesRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=AllcourcesrecyclerviewlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.CourseTitle.text= list[position].CT
        holder.binding.CourseCode.text= list[position].CC
        val currentItem=list[position]
        holder.itemView.setOnClickListener {
            val bottomSheetFragment=BottomSheetBMPFragment()
            bottomSheetFragment.show((context as AppCompatActivity).supportFragmentManager,bottomSheetFragment.tag)
        }
    }
    inner class ViewHolder(var binding :AllcourcesrecyclerviewlayoutBinding): RecyclerView.ViewHolder(binding.root)

}