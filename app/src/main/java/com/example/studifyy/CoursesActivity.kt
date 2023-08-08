package com.example.studifyy
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studifyy.databinding.ActivityCourcesBinding
import com.google.firebase.firestore.FirebaseFirestore

class CoursesActivity: AppCompatActivity() {
    private lateinit var binding:ActivityCourcesBinding
    private var db= FirebaseFirestore.getInstance()
    private lateinit var adapter:CoursesRecyclerViewAdapter
    private  var documentId:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCourcesBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        val sharedPreferences=getSharedPreferences("OfflineData",Context.MODE_PRIVATE)
        val selectedProgram=sharedPreferences.getString("Store",null)
        showCourses(selectedProgram)
        binding.AllCourseRecyclerview.layoutManager= GridLayoutManager(this,2)
        adapter= CoursesRecyclerViewAdapter{courses->
             documentId=courses.documentId
            Log.d("(((((((((((TARIQ)))))))",documentId)
            openMainActivity(selectedProgram,documentId)
        }
        binding.AllCourseRecyclerview.adapter=adapter
        binding.profile.setOnClickListener {
            val bottomSheet=BottomSheetCoursesFragment()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
            Log.d("(((((((((((TARIQ)))))))",documentId)
        }
    }
    private fun openMainActivity(selectedProgram: String?, documentId: String){
        val intent=Intent(this,MainActivity::class.java)
        intent.putExtra("selectedProgram",selectedProgram)
        intent.putExtra("documentId",documentId)
        startActivity(intent)
    }
    private fun showCourses(selectedProgram: String?) {
        if (selectedProgram==null){
            return
        }
        val proRef=db.collection("Programs").document(selectedProgram)
        val coursesRef=proRef.collection("Courses")
        coursesRef.get().addOnSuccessListener { querySnapshot->
            val coursesList= mutableListOf<CoursesModel>()
            for (document in querySnapshot){
                val coursesName=document.getString("CoursesName")?: ""
                val coursesCode=document.getString("CoursesCode")?: ""
                val documentId=document.id
                coursesList.add(CoursesModel(coursesCode,coursesName,documentId))
            }
            adapter.setCourses(coursesList)
        }
            .addOnFailureListener {

            }
    }

}