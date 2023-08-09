package com.example.studifyy
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studifyy.databinding.FragmentBooksBinding
import com.google.firebase.firestore.FirebaseFirestore
class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private var db = FirebaseFirestore.getInstance()
    private var adapter = AllMaterialAdapter()
    private lateinit var selectedProgram: String
    private lateinit var documentId: String
    private var originalItemList: List<MaterialModel> = emptyList()
    val bookList = mutableListOf<MaterialModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedProgram = arguments?.getString("selectedProgram") ?: ""
        documentId = arguments?.getString("documentId") ?:""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksBinding.inflate(inflater, container, false)
        binding.BooksMaterialRecyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = AllMaterialAdapter()
        binding.BooksMaterialRecyclerView.adapter = adapter
        fetchData()
        binding.DrawerMenu.setOnClickListener {
            val bottomSheet = BottomSheetCoursesFragment()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
        binding.BooksSearch.setOnQueryTextListener(object :android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) {

                        adapter.setCourses(originalItemList)
                    } else {
                        val filteredItems = originalItemList.filter { item -> item.TopicName .contains(it, ignoreCase = true)}
                        adapter.setCourses(filteredItems)
                    }
                }
                return true
            }
        })
        return binding.root
    }

    companion object {
        fun newInstance(selectedProgram: String, documentId: String): PapersFragment {
            val args = Bundle().apply {
                putString("selectedProgram", selectedProgram)
                putString("documentId", documentId)
            }
            val fragment = PapersFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private fun fetchData() {
        val proRef = db.collection("Programs").document(selectedProgram)
        val coursesRef = proRef.collection("Courses").document(documentId)
        coursesRef.collection("Books")
            .get().addOnSuccessListener { query ->
                for (document in query) {
                    val notesName = document.getString("TopicName") ?: ""
                    val url = document.getString("Url") ?: ""
                    bookList.add(MaterialModel(notesName, url))
                }
                originalItemList = bookList.toList()
                adapter.setCourses(bookList)
            }
            .addOnFailureListener {

            }
    }
}