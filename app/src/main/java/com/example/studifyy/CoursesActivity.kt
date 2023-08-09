package com.example.studifyy
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studifyy.databinding.ActivityCourcesBinding
import com.google.firebase.firestore.FirebaseFirestore

class CoursesActivity: AppCompatActivity() {
    private lateinit var binding:ActivityCourcesBinding
    private var db= FirebaseFirestore.getInstance()
    private lateinit var adapter:CoursesRecyclerViewAdapter
    private  var documentId:String = ""
    private var originalItemList: List<CoursesModel> = emptyList()
    private val coursesList= mutableListOf<CoursesModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourcesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("OfflineData", Context.MODE_PRIVATE)
        val selectedProgram = sharedPreferences.getString("Store", null)
        showCourses(selectedProgram)
        binding.AllCourseRecyclerview.layoutManager = GridLayoutManager(this, 2)
        adapter = CoursesRecyclerViewAdapter { courses ->
            documentId = courses.documentId
            openMainActivity(selectedProgram, documentId)
        }
        binding.AllCourseRecyclerview.adapter = adapter
        binding.profile.setOnClickListener {
            val bottomSheet = BottomSheetCoursesFragment()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }
        binding.coursesSearchBar.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) {

                        adapter.setCourses(originalItemList)
                    } else {
                        val filteredItems = originalItemList.filter { item -> item.CT.contains(it, ignoreCase = true)||item.CC.contains(it,ignoreCase = true) }
                        adapter.setCourses(filteredItems)
                    }
                }
                return true
            }

        })
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
            for (document in querySnapshot){
                val coursesName=document.getString("CoursesName")?: ""
                val coursesCode=document.getString("CoursesCode")?: ""
                val documentId=document.id
                coursesList.add(CoursesModel(coursesCode,coursesName,documentId))
            }
            originalItemList = coursesList.toList()
            adapter.setCourses(coursesList)
        }
            .addOnFailureListener {

            }
    }

}