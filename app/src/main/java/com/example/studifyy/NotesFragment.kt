package com.example.studifyy
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studifyy.databinding.FragmentNotesBinding
import com.google.firebase.firestore.FirebaseFirestore

class NotesFragment : Fragment() {
    private  lateinit var binding:FragmentNotesBinding
    private var db=FirebaseFirestore.getInstance()
    private var adapter=AllMaterialAdapter()
    private lateinit var selectedProgram:String
    private lateinit var documentId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedProgram= arguments?.getString("selectedProgram") ?:""
        documentId= arguments?.getString("documentId") ?:""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentNotesBinding.inflate(inflater,container,false)
        binding.NotesMaterialRecyclerView.layoutManager=GridLayoutManager(context,2)
        adapter=AllMaterialAdapter()
        binding.NotesMaterialRecyclerView.adapter=adapter
        fetchData()
        binding.DrawerMenu.setOnClickListener {
            val bottomSheet=BottomSheetCoursesFragment()
            bottomSheet.show(parentFragmentManager,bottomSheet.tag)
        }
        return binding.root
    }
    companion object{
        fun newInstance(selectedProgram:String,documentId:String):NotesFragment{
            val args=Bundle().apply {
                putString("selectedProgram",selectedProgram)
                putString("documentId",documentId)
            }
            val fragment=NotesFragment()
            fragment.arguments=args
            return fragment
        }
    }
    private fun fetchData(){
        val proRef=db.collection("Programs").document(selectedProgram)
        val coursesRef=proRef.collection("Courses").document(documentId)
        coursesRef.collection("Notes")
            .get().addOnSuccessListener {query->
                val noteList= mutableListOf<MaterialModel>()
                for (document in query){
                    val notesName=document.getString("TopicName")?:""
                     val url=document.getString("Url")?:""
                    noteList.add(MaterialModel(notesName,url))
                }
                adapter.setCourses(noteList)
            }
            .addOnFailureListener {

            }
    }
}