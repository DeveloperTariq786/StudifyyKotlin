package com.example.studifyy
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.studifyy.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val selectedProgram=intent.getStringExtra("selectedProgram")?:""
        val documentId=intent.getStringExtra("documentId")?:""
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Notes->replaceFragment(NotesFragment.newInstance(selectedProgram,documentId))
                R.id.Books->replaceFragment(BooksFragment.newInstance(selectedProgram,documentId))
                R.id.Papers->replaceFragment(PapersFragment.newInstance(selectedProgram,documentId))
                else->{

                }
            }
            true
        }
        replaceFragment(NotesFragment.newInstance(selectedProgram,documentId))
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment).commit()
    }
}
/*

Lottie.initialize(
    LottieConfig.Builder()
        .setEnableSystraceMarkers(true)
        .setNetworkFetcher(...)
        .setNetworkCacheDir(...)
        .setEnableNetworkCache(false)
)
<com.airbnb.lottie.LottieAnimationView
        android:id="@+id/animation_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        app:lottie_rawRes="@raw/hello_world"
        // or
        app:lottie_fileName="hello_world.json"

        // Loop indefinitely
        app:lottie_loop="true"
        // Start playing as soon as the animation is loaded
        app:lottie_autoPlay="true" />
 */