package com.diewland.hmdemo

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.diewland.hmslideshow.FullScreenSlideshow
import com.diewland.hmslideshow.HandmadeSlideshow

const val TAG = "HMSLIDE_DEMO"

class MainActivity : AppCompatActivity() {

    lateinit var hm: HandmadeSlideshow
    lateinit var fs: FullScreenSlideshow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create playlist
        val playlistA = arrayListOf(
            "/sdcard/video1.mp4",
            "/sdcard/image1.jpg",
            "/sdcard/video2.mp4",
        )
        val playlistB = arrayListOf(
            "/sdcard/image2.jpg",
            "/sdcard/video3.mp4",
        )

        // prepare surface
        val surface = findViewById<LinearLayout>(R.id.slide1)

        // init handmade slideshow and play
        hm = HandmadeSlideshow(this, surface, playlistA)
        hm.setPhotoDelay(5)
        hm.start()

        // init fullscreen slideshow and link with handmade slideshow
        fs = FullScreenSlideshow(this, 1080, 1920) {
            hm.start() // start handmade slideshow when exit fullscreen
        }
        fs.slideshow.updateMedia(playlistB)
        fs.slideshow.setPhotoDelay(5)
        findViewById<Button>(R.id.btn_fullscreen).setOnClickListener {
            hm.stop() // stop handmade slideshow before enter fullscreen
            fs.start()
        }
    }

    override fun onResume() {
        super.onResume()
        hm.onResume() // <-- binded
    }

    override fun onPause() {
        super.onPause()
        hm.onPause() // <-- binded
    }

    override fun onDestroy() {
        super.onDestroy()
        hm.onDestroy() // <-- binded
        fs.destroy() // <-- binded
    }

}
