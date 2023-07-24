package com.example.studifyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studifyy.databinding.ActivityCourcesBtechBinding

class BTechCourcesActivity: AppCompatActivity() {
    private lateinit var binding:ActivityCourcesBtechBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCourcesBtechBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.AllCourseRecyclerview.layoutManager=LinearLayoutManager(this)
        val data=BTechCoursesDetailDataFile.items
        val adapter=CoursesRecyclerViewAdapter(data as ArrayList<CoursesModel>,this)
        binding.AllCourseRecyclerview.adapter=adapter
    }

}