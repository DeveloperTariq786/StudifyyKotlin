package com.example.studifyy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studifyy.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.AllMaterialRecyclerView.layoutManager=GridLayoutManager(this,3)
        val list=ArrayList<MaterialModel>()
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        list.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        val adapter=AllMaterialAdapter(list,this)
        binding.AllMaterialRecyclerView.adapter=adapter
        //////////////////////////////////////////////////
        binding.RecentUseRecycleView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val recentList=ArrayList<MaterialModel>()
        recentList.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        recentList.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        recentList.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        recentList.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        recentList.add(MaterialModel(R.drawable.image,"8 Queen Problem"))
        binding.RecentUseRecycleView.adapter=adapter
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