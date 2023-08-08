package com.example.studifyy
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import com.example.studifyy.databinding.ActivitySplashBinding
import java.util.Timer
import java.util.TimerTask

class SplashActivity:AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed(2000){
            startActivity(Intent(this,RegistrationActivity::class.java))
            finish()
        }
    }
}
