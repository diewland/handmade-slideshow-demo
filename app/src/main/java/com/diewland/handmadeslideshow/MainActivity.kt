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
            "/sdcard/SAMPLES/video1.mp4",
            "/sdcard/SAMPLES/logo1.png",
            "/sdcard/SAMPLES/pic2.png",
            "/sdcard/SAMPLES/video2.mp4",
            "/sdcard/SAMPLES/logo2.png",
            "/sdcard/SAMPLES/logo3.png",
            "/sdcard/SAMPLES/video3.mp4",
            "/sdcard/SAMPLES/logo4.png",
            "/sdcard/SAMPLES/video4.mp4",
            "/sdcard/SAMPLES/video5.mp4"
        )

        // initialize root view
        val mySlideshow: LinearLayout = findViewById(R.id.my_slideshow)

        // initialize homemade slideshow
        val hmSlideshow = HandmadeSlideshow(this, mySlideshow, mediaList)
        hmSlideshow.setPhotoDelay(5) // sec

        // play slideshow
        hmSlideshow.play()
    }
}
