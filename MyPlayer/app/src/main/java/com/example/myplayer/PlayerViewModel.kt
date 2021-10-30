package com.example.myplayer

import android.media.MediaPlayer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {

     val mediaPlayer = MyMediaPlayer()

    private val _progressBarVisibility = MutableLiveData(View.VISIBLE)
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _videoResolution = MutableLiveData(Pair(0, 0))
    val videoResolution: LiveData<Pair<Int, Int>> = _videoResolution

    init {
        loadVideo()
    }

    fun loadVideoPath(videoPath: String) {
        mediaPlayer.apply {
//            reset()
            _progressBarVisibility.value = View.INVISIBLE

            setDataSource(videoPath)
            setOnPreparedListener {
                _progressBarVisibility.value = View.INVISIBLE
                isLooping = true
                it.start()

            }

            setOnVideoSizeChangedListener { mp, width, height ->
                _videoResolution.value = Pair(width, height)

            }

            prepareAsync()

        }

    }


    fun loadVideo() {
        //            val videoPath = "android.resource://$packageName/${R.raw.testhtml5}"
        val videoPath = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"

        loadVideoPath(videoPath)

    }

    override fun onCleared() {
        super.onCleared()

        mediaPlayer.release()
    }

}