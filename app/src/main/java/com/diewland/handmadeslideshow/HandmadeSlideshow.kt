package com.diewland.handmadeslideshow

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout

class HandmadeSlideshow constructor(ctx: Context,
                                    private val rootView: LinearLayout,
                                    private val mediaList: ArrayList<String>) {

    private val TAG = "HMSLIDESHOW"
    private val imageView = ImageView(ctx)
    private var mediaIndex = 0

    init {
        // TODO show require permission dialog ( if required )
        //      * read internal storage

        imageView.layoutParams = getLLParams()
    }

    /* ---------- PUBLIC FUNCTIONS ---------- */

    fun play() {
        if (mediaList.size == 0) return

        // add image view into rootView
        rootView.removeAllViews()
        rootView.addView(imageView)

        // render first image
        setImage(mediaList[0])

        // TODO test slideshow
        Handler().postDelayed({
            setImage(mediaList[1])

            Handler().postDelayed({
                setImage(mediaList[2])
            }, 5 * 1000)

        }, 5 * 1000)
    }

    /* ---------- INTERNAL FUNCTIONS ---------- */

    private fun setImage(filePath: String) {
        Log.d(TAG, "set imageView source -> $filePath")

        // re-mount imageView
        rootView.removeAllViews()
        rootView.addView(imageView)

        // set image source
        val bmp = BitmapFactory.decodeFile(filePath)
        imageView.setImageBitmap(bmp)
    }

    private fun getLLParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }
}