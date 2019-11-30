package com.diewland.handmadeslideshow

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import java.io.File

class HandmadeSlideshow constructor(ctx: Context,
                                    private val rootView: LinearLayout,
                                    private val mediaList: ArrayList<String>) {

    private val TAG = "HMSLIDESHOW"

    // views
    private val imageView = ImageView(ctx)

    private val mediaSize = mediaList.size
    private var mediaIndex = 0
    private var photoDelay:Long = 60 // seconds

    init {
        // TODO show require permission dialog ( if required )
        //      * read internal storage

        imageView.layoutParams = getLLParams()
    }

    /* ---------- PUBLIC FUNCTIONS ---------- */

    fun setPhotoDelay(delay: Long) {
        photoDelay = delay
    }

    fun play() {
        if (mediaSize == 0) return

        // add image view into rootView
        // TODO check image / video from file extension
        rootView.removeAllViews()
        rootView.addView(imageView)

        // render image
        setImage(mediaList[mediaIndex])

        // set delay before show next media
        Handler().postDelayed({
            next()
        }, photoDelay * 1000)
    }


    /* ---------- INTERNAL FUNCTIONS ---------- */

    // control flow function
    private fun next() {
        if (mediaIndex < mediaSize-1) {
            mediaIndex++
        }
        else {
            mediaIndex = 0
        }
        play()
    }

    private fun setImage(filePath: String) {
        val f = File(filePath)
        if (!f.exists()) {
            Log.d(TAG, "#$mediaIndex [SKIP] $filePath <-- File not found")
        }
        else if (!checkImageExt(f.extension)) {
            Log.d(TAG, "#$mediaIndex [SKIP] $filePath <-- JPG and PNG only")
        }
        else {
            Log.d(TAG, "#$mediaIndex [PASS] $filePath")

            // re-mount imageView
            rootView.removeAllViews()
            rootView.addView(imageView)

            // set image source
            val bmp = BitmapFactory.decodeFile(f.absolutePath)
            imageView.setImageBitmap(bmp)
        }
    }

    private fun checkImageExt(ext: String): Boolean {
        val validExt = arrayListOf("png", "jpg")
        return validExt.contains(ext.toLowerCase())
    }

    private fun getLLParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }
}