package com.diewland.handmadeslideshow

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.VideoView
import java.io.File

class HandmadeSlideshow constructor(ctx: Context,
                                    rootView: LinearLayout,
                                    private val mediaList: ArrayList<String> = arrayListOf()) {

    private val TAG = "HMSLIDESHOW"
    private val TYPE_IMAGE = "TYPE_IMAGE"
    private val TYPE_VIDEO = "TYPE_VIDEO"
    private val EXT_IMAGE = arrayListOf("jpg", "png")
    private val EXT_VIDEO = arrayListOf("mp4")

    // views
    private val imageView = ImageView(ctx)
    private val videoView = VideoView(ctx)

    // config
    private var photoDelay:Long = 60 // seconds

    // app state
    private var mediaIndex = 0
    private var mediaType = TYPE_IMAGE
    private var isPlaying = false

    // image thread
    private var imgHandler = Handler()
    private var playNextImage: Runnable

    init {
        // TODO show read-internal-storage permission dialog ( if required )

        // add media views to root
        rootView.removeAllViews()
        rootView.addView(imageView)
        rootView.addView(videoView)

        // set up layout params
        imageView.layoutParams = getLLParams()
        videoView.layoutParams = getLLParams()

        // hide all
        imageView.visibility = View.GONE
        videoView.visibility = View.GONE

        // play next slide when image/video play done
        playNextImage = Runnable { next() }
        videoView.setOnCompletionListener { next() }
    }

    /* ---------- CONTROL SLIDESHOW ---------- */

    fun start() {
        isPlaying = true
        play()
    }

    fun stop() {
        isPlaying = false
        when (mediaType) {
            TYPE_IMAGE -> {
                imgHandler.removeCallbacks(playNextImage)
            }
            TYPE_VIDEO -> {
                if (videoView.isPlaying) videoView.stopPlayback()
            }
        }
    }

    /* ---------- UPDATE SLIDESHOW ---------- */

    fun updateMedia(newMediaList: ArrayList<String>) {
        clearMedia()
        for (m in newMediaList) {
            mediaList.add(m)
        }
    }

    fun clearMedia() {
        mediaList.clear()
    }

    fun setPhotoDelay(delay: Long) {
        photoDelay = delay
    }

    /* ---------- BIND APP EVENTS ---------- */

    fun onResume() {
        if (!isPlaying) start()
    }

    fun onPause() {
        if (isPlaying) stop()
    }

    /* ---------- INTERNAL FUNCTION(S) ---------- */

    private fun play() {
        if (mediaList.size == 0) return

        // build f
        val filePath = mediaList[mediaIndex]
        val f = File(filePath)

        // file not found
        if (!f.exists()) {
            Log.d(TAG, "#$mediaIndex [SKIP] $filePath <-- File not found")
        }

        // play image
        else if (checkImageExt(f.extension)) {
            Log.d(TAG, "#$mediaIndex [PASS] $filePath")
            playImage(f)

            // do not refresh if have one media
            if (mediaList.size == 1) return

            imgHandler.postDelayed(playNextImage, photoDelay * 1000)
        }

        // play video
        else if (checkVideoExt(f.extension)) {
            Log.d(TAG, "#$mediaIndex [PASS] $filePath")
            playVideo(f)
        }

        // extension does not support
        else {
            Log.d(TAG, "#$mediaIndex [SKIP] $filePath <-- Extension does not support")
        }
    }

    // control flow
    private fun next() {
        if (mediaIndex < mediaList.size-1) {
            mediaIndex++
        }
        else {
            mediaIndex = 0
        }
        play()
    }

    private fun playImage(f: File) {
        mediaType = TYPE_IMAGE

        // toggle media view
        videoView.visibility = View.GONE
        imageView.visibility = View.VISIBLE

        // set image
        val bmp = BitmapFactory.decodeFile(f.absolutePath)
        imageView.setImageBitmap(bmp)
    }

    private fun playVideo(f: File) {
        mediaType = TYPE_VIDEO

        // toggle media view
        imageView.visibility = View.GONE
        videoView.visibility = View.VISIBLE

        // play video
        videoView.setVideoURI(Uri.fromFile(f))
        videoView.start()
    }

    private fun checkImageExt(ext: String): Boolean {
        return EXT_IMAGE.contains(ext.toLowerCase())
    }

    private fun checkVideoExt(ext: String): Boolean {
        return EXT_VIDEO.contains(ext.toLowerCase())
    }

    private fun getLLParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

}