package com.diewland.handmadeslideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    lateinit var hmSlideshow :HandmadeSlideshow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define media list
        val mediaList = arrayListOf(
            "/sdcard/SAMPLES/pic1.jpg",
            "/sdcard/SAMPLES/pic2.png",
            "/sdcard/SAMPLES/video5.mp4"
        )

        // initialize root view
        val mySlideshow: LinearLayout = findViewById(R.id.my_slideshow)

        // initialize homemade slideshow
        hmSlideshow = HandmadeSlideshow(this, mySlideshow, mediaList)
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
