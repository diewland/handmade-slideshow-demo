package com.diewland.hmdemo

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.diewland.hmslideshow.HandmadeSlideshow

class MainActivity : AppCompatActivity() {

    lateinit var hm: HandmadeSlideshow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create playlist
        val playlist = arrayListOf(
            "/sdcard/video1.mp4",
            "/sdcard/video2.mp4",
            "/sdcard/video3.mp4",
        )

        // prepare surface
        val surface = findViewById<LinearLayout>(R.id.slide1)

        // init handmade slideshow and play
        hm = HandmadeSlideshow(this, surface, playlist)
        hm.start()
    }

    override fun onResume() {
        super.onResume()
        hm.onResume() // <--
    }

    override fun onPause() {
        super.onPause()
        hm.onPause() // <--
    }

    override fun onDestroy() {
        super.onDestroy()
        hm.stop() // <--
    }

}
