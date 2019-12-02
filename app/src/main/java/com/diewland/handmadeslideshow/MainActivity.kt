package com.diewland.handmadeslideshow

import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var hmSlideshow :HandmadeSlideshow

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdPath = Environment.getExternalStorageDirectory().toString()

        // define media list
        val mediaList = arrayListOf(
            "$sdPath/SAMPLES/pic1.jpg",
            "$sdPath/SAMPLES/pic2.png",
            "$sdPath/SAMPLES/video5.mp4"
        )

        // initialize root view
        val mySlideshow: LinearLayout = findViewById(R.id.my_slideshow)

        // initialize homemade slideshow
        hmSlideshow = HandmadeSlideshow(this, mySlideshow)
        hmSlideshow.updateMedia(mediaList)
        hmSlideshow.setPhotoDelay(5) // sec
    }

    override fun onResume() {
        super.onResume()
        hmSlideshow.onResume()
    }

    override fun onPause() {
        super.onPause()
        hmSlideshow.onPause()
    }

}
