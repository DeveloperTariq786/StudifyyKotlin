package com.example.studifyy
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studifyy.databinding.AllcourcesrecyclerviewlayoutBinding

class CoursesRecyclerViewAdapter(private val itemClickListener: (CoursesModel)->Unit): RecyclerView.Adapter<CoursesRecyclerViewAdapter.ViewHolder>() {
    private var list: List<CoursesModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AllcourcesrecyclerviewlayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    fun setCourses(courses: List<CoursesModel>) {
        list = courses
        notifyDataSetChanged()
    }
    inner class ViewHolder(private var binding: AllcourcesrecyclerviewlayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CoursesModel) {
            val isConnected= isOnline(itemView.context)
            binding.CourseCode.text = data.CC
            binding.CourseTitle.text = data.CT
            itemView.setOnClickListener {
               if (isConnected){
                   itemClickListener(data)
               }
                else{
                    val massage= "Please Check Internet Connection!!!"
                   Toast.makeText(itemView.context,massage, Toast.LENGTH_LONG).show()
               }
            }
        }

    }
}

/* holder.itemView.setOnClickListener {
           val bottomSheetFragment=BottomSheetBMPFragment()
           bottomSheetFragment.show((context as AppCompatActivity).supportFragmentManager,bottomSheetFragment.tag)
       }*/