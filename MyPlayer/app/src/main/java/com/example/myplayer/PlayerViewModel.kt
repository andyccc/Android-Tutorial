package com.example.myplayer

import android.media.MediaPlayer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class PlayerStatus {
    Playing, Paused, Completed, NotReady
}

class PlayerViewModel : ViewModel() {

    val mediaPlayer = MyMediaPlayer()

    private var controllerShowTime = 0L

    private val _progressBarVisibility = MutableLiveData(View.VISIBLE)
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _controllerFrameVisibility = MutableLiveData(View.INVISIBLE)
    val controllerFrameVisibility: LiveData<Int> = _controllerFrameVisibility

    private val _bufferPercent = MutableLiveData(0)
    val bufferPercent: LiveData<Int> = _bufferPercent

    private val _videoResolution = MutableLiveData(Pair(0, 0))
    val videoResolution: LiveData<Pair<Int, Int>> = _videoResolution

    private val _playerStatus = MutableLiveData<PlayerStatus>(PlayerStatus.NotReady)
    val playerStatus: LiveData<PlayerStatus> = _playerStatus

    init {
        loadVideo()
    }

    fun loadVideoPath(videoPath: String) {
        mediaPlayer.apply {
//            reset()
            _progressBarVisibility.value = View.VISIBLE
            _playerStatus.value = PlayerStatus.NotReady

            setDataSource(videoPath)
            setOnPreparedListener {
                _progressBarVisibility.value = View.INVISIBLE
//                isLooping = true
                it.start()
                _playerStatus.value = PlayerStatus.Playing

            }

            setOnVideoSizeChangedListener { mp, width, height ->
                _videoResolution.value = Pair(width, height)

            }

            setOnBufferingUpdateListener { mp, percent ->
                _bufferPercent.value = percent
            }

            setOnCompletionListener {
                _playerStatus.value = PlayerStatus.Completed

            }

            setOnSeekCompleteListener {
                mediaPlayer.start()
                _progressBarVisibility.value = View.INVISIBLE
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

    fun emmitVideoResolution() {
        _videoResolution.value = _videoResolution.value
    }

    fun toggleControllerVisibility() {
        if (_controllerFrameVisibility.value == View.INVISIBLE) {
            _controllerFrameVisibility.value = View.VISIBLE
            controllerShowTime = System.currentTimeMillis()

            // 延迟3秒隐藏
            viewModelScope.launch {
                delay(3000)
                // 防止重复点击，进行处理
                if (System.currentTimeMillis() - controllerShowTime > 3000) {
                    _controllerFrameVisibility.value = View.INVISIBLE
                }
            }
        } else {
            _controllerFrameVisibility.value = View.INVISIBLE
        }
    }

    fun togglePlayerStatus() {
        when(_playerStatus.value) {
            PlayerStatus.Playing -> {
                mediaPlayer.pause()
                _playerStatus.value = PlayerStatus.Paused
            }
            PlayerStatus.Paused -> {
                mediaPlayer.start()
                _playerStatus.value = PlayerStatus.Playing
            }
            PlayerStatus.Completed -> {
                mediaPlayer.start()
                _playerStatus.value = PlayerStatus.Playing
            }
            else -> return

        }

    }


    fun playerSeekToProgress(process: Int) {
        _progressBarVisibility.value = View.VISIBLE
        mediaPlayer.seekTo(process)
    }

}