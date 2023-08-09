package com.example.studifyy
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
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
            binding.CourseCode.text = data.CC
            binding.CourseTitle.text = data.CT
            itemView.setOnClickListener {
                itemClickListener(data)
            }
        }

    }
}

/* holder.itemView.setOnClickListener {
           val bottomSheetFragment=BottomSheetBMPFragment()
           bottomSheetFragment.show((context as AppCompatActivity).supportFragmentManager,bottomSheetFragment.tag)
       }*/