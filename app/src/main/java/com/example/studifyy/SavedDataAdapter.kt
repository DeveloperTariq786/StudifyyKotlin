package com.example.studifyy
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studifyy.databinding.FrontpagelayoutBinding

class SavedDataAdapter(private var items: List<SavedData>) : RecyclerView.Adapter<SavedDataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedDataAdapter.ViewHolder {
        val binding =FrontpagelayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun update(newItems:List<SavedData>){
        items=newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: FrontpagelayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: SavedData) {
            binding.TopicName.text = items.TopicName
            binding.root.setOnClickListener {
                    openMaterialUsingPDF(items.url, itemView.context)
            }
        }

        private fun openMaterialUsingPDF(url: String, context: Context) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(itemView.context, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}