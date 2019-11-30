package com.diewland.handmadeslideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define media list
        val mediaList = arrayListOf(
            "/sdcard/SAMPLES/pic1.jpg",
            "/sdcard/SAMPLES/pic2.png",
            "/sdcard/SAMPLES/logo1.png",
            "/sdcard/SAMPLES/logo2.png"
        )

        // initialize root view
        val mySlideshow: LinearLayout = findViewById(R.id.my_slideshow)

        // initialize homemade slideshow
        val hmSlideshow = HandmadeSlideshow(this, mySlideshow, mediaList)

        // play slideshow
        hmSlideshow.play()
    }
}
