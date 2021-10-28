package com.example.videoview

import android.media.PlaybackParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var savePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoPlay2()

    }

    fun videoPlay0() {
        val videoPath = "android.resource://$packageName/${R.raw.testhtml5}"
        videoView.setVideoPath(videoPath)

        videoView.start()

    }

    fun videoPlay1() {
        val videoPath = "android.resource://$packageName/${R.raw.testhtml5}"
        videoView.setVideoPath(videoPath)
        videoView.setMediaController(MediaController(this))
        videoView.setOnPreparedListener {
//            it.seekTo()
            progressBar.max = it.duration
            it.isLooping = true
            it.playbackParams = PlaybackParams().apply {
//                speed = 2f
//                pitch = 2f
            }
            it.start()
        }
    }

    fun videoPlay2() {
//        val videoPath = "android.resource://$packageName/${R.raw.testhtml5}"
        val videoPath = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        videoView.setVideoPath(videoPath)
        videoView.setOnPreparedListener {

            progressBar2.visibility = View.INVISIBLE
            progressBar.max = it.duration
            it.isLooping = true
            it.playbackParams = PlaybackParams().apply {
//                speed = 2f
//                pitch = 2f
            }
            it.seekTo(savePosition)
            it.start()
        }

        lifecycleScope.launch {
            while (true) {
                if (videoView.isPlaying) {
                    progressBar.progress = videoView.currentPosition
                }
                delay(500)
            }
        }
    }


    override fun onPause() {
        super.onPause()
        savePosition = videoView.currentPosition
    }
}