package com.example.studifyy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        val data=BTechCoursesDetailDataFile.getData()
        val adapter=CoursesRecyclerViewAdapter(data,this)
        binding.AllCourseRecyclerview.adapter=adapter
    }

}