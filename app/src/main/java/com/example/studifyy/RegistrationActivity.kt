package com.example.studifyy
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studifyy.databinding.ActivityRegistrationBinding
class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}