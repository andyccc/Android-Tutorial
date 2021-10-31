package com.example.videopager

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment(private  val url: String) : Fragment() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer.apply {
            setOnPreparedListener {
                progressBarH.max = mediaPlayer.duration
//                it.start()
                seekTo(1)
                progressBar.visibility = View.INVISIBLE
            }

//            setOnVideoSizeChangedListener { mp, width, height ->
//                resizePlayer(width, height)
//            }

            setDataSource(url)
            prepareAsync()
            progressBar.visibility = View.VISIBLE
        }

        lifecycleScope.launch {
            while (true) { // 这里不会出现死循环
                progressBarH.progress = mediaPlayer.currentPosition
                delay(500)
            }
        }

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                mediaPlayer.setDisplay(holder)
                mediaPlayer.setScreenOnWhilePlaying(true)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {

            }
        })


    }
//    playerFrame.height * width / height
    //      w        width
    //       x       height
    private fun resizePlayer(width: Int, height: Int) {
        if (width == 0 || height == 0) return
        surfaceView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            playerFrame.width * height / width,
            Gravity.CENTER
        )
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
        lifecycleScope.launch {
            while (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                delay(500)
            }
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }
}