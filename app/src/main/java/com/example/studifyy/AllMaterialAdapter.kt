package com.example.studifyy
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studifyy.databinding.FrontpagelayoutBinding
import com.squareup.picasso.Picasso

class AllMaterialAdapter : RecyclerView.Adapter<AllMaterialAdapter.ViewHolder>() {
    private var list:List<MaterialModel> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=FrontpagelayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item=list[position]
        holder.bind(item)
    }
    fun setCourses(notes:List<MaterialModel>) {
        list=notes
        notifyDataSetChanged()
    }
    inner class ViewHolder(private var binding :FrontpagelayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data:MaterialModel){
            binding.TopicName.text=data.TopicName
            binding.root.setOnClickListener {
                 Log.d("Error",data.Url)
                 openMaterialUsingPDF(data.Url,itemView.context)
            }
        }
        private fun openMaterialUsingPDF(url: String,context:Context){
            val intent=Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url),"application/pdf")
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            try {
                context.startActivity(intent)
            }
            catch (e:Exception){
                Toast.makeText(itemView.context,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }

}