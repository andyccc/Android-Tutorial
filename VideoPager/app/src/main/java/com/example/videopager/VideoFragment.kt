package com.example.videopager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.fragment_video.*


private val videoUrls = listOf(
    "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
    "http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
    "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4",
    "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4",
    "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4",
    "http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4",
    "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4",
    "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4",
    "http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4",
    "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4",
    "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4",
    "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4",
    "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4",
    "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4",
    "http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4"
)

class VideoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewPager.apply {
            adapter = object : FragmentStateAdapter (this@VideoFragment) {
                override fun getItemCount() = videoUrls.size

                override fun createFragment(position: Int) = PlayerFragment(videoUrls[position])
            }

            offscreenPageLimit = 5
        }

    }

}