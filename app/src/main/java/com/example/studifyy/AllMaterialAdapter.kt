package com.example.studifyy
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studifyy.databinding.FrontpagelayoutBinding
class AllMaterialAdapter(private var list:ArrayList<MaterialModel>,private var context:Context): RecyclerView.Adapter<AllMaterialAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=FrontpagelayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item:MaterialModel=list[position]
        holder.binding.FrontPage.setImageResource(item.FrontPageCover)
        holder.binding.TopicName.text=list[position].TopicName
    }
    inner class ViewHolder(var binding :FrontpagelayoutBinding): RecyclerView.ViewHolder(binding.root)

}