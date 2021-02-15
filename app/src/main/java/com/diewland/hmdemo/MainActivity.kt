package com.diewland.hmdemo

import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.diewland.hmslideshow.HandmadeSlideshow

class MainActivity : AppCompatActivity() {

    val layoutIds = listOf(
        R.id.p1,
        R.id.p2,
        R.id.p3,
        R.id.p4,
        R.id.p5,
        R.id.p6,
        R.id.p7,
        R.id.p8,
        R.id.p9,
        R.id.p10
    )
    val hmList = arrayListOf<HandmadeSlideshow>()

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdPath = Environment.getExternalStorageDirectory().toString()

        // define media list
        val mediaList = arrayListOf(
            "$sdPath/SAMPLES/video3.mp4",
            "$sdPath/SAMPLES/video5.mp4"
        )

        // init hm list
        layoutIds.forEach {
            // initialize root view
            val ll = findViewById<LinearLayout>(it)
            // initialize handmade slideshow
            val hm = HandmadeSlideshow(this, ll)
            hm.updateMedia(mediaList)
            // add to hm list
            hmList.add(hm)
        }
    }

    override fun onResume() {
        super.onResume()
        hmList.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        hmList.forEach { it.onPause() }
    }

}
