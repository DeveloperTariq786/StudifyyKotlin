package com.example.studifyy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studifyy.databinding.ActivitySavedBinding

class SavedActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySavedBinding
    private lateinit var savedDAOClass:SavedDAOClass
    private lateinit var adapter:SavedDataAdapter
    private lateinit var viewModelClass: ViewModelClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelClass= ViewModelProvider(this).get(ViewModelClass::class.java)
        viewModelClass.roomLiveData.observe(this) { item ->
            adapter.update(item)
        }
       adapter= SavedDataAdapter(emptyList())
        binding.SavedMaterialRecyclerView.adapter=adapter
        binding.SavedMaterialRecyclerView.layoutManager = GridLayoutManager(this,2)

    }
    }