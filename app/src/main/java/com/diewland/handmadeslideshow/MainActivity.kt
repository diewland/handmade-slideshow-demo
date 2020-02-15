package com.diewland.handmadeslideshow

import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var hmSlideshow :HandmadeSlideshow
    lateinit var hmSlideshow2 :HandmadeSlideshow

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdPath = Environment.getExternalStorageDirectory().toString()

        // define media list
        val mediaList = arrayListOf(
            //"$sdPath/SAMPLES/pic1.jpg",
            //"$sdPath/SAMPLES/pic2.png",
            "$sdPath/SAMPLES/video5.mp4"
        )
        val mediaList2 = arrayListOf(
            "$sdPath/SAMPLES/video3.mp4"
        )

        // initialize root view
        val mySlideshow: LinearLayout = findViewById(R.id.my_slideshow)
        val mySlideshow2: LinearLayout = findViewById(R.id.my_slideshow2)

        // initialize homemade slideshow
        hmSlideshow = HandmadeSlideshow(this, mySlideshow)
        hmSlideshow.updateMedia(mediaList)
        //hmSlideshow.setPhotoDelay(5) // sec

        // initialize homemade slideshow #2
        hmSlideshow2 = HandmadeSlideshow(this, mySlideshow2, muteVideo = true)
        hmSlideshow2.updateMedia(mediaList2)
        //hmSlideshow2.setPhotoDelay(5) // sec
    }

    override fun onResume() {
        super.onResume()
        hmSlideshow.onResume()
        hmSlideshow2.onResume()
    }

    override fun onPause() {
        super.onPause()
        hmSlideshow.onPause()
        hmSlideshow2.onPause()
    }

}
