package com.example.pagergallery

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_galley.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  val galleryViewModel by viewModels<GalleryViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galley, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GalleyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GalleyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.swipeIndicator -> {
                swipeRefresh.isRefreshing = true
                Handler().postDelayed(Runnable {
                    galleryViewModel.resetQuery()
                }, 1000)
            }
            R.id.menuRetry -> {
                galleryViewModel.retry()

            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)


        val galleryAdapter = GalleryAdapter(null)

        galleryViewModel.pagedListLiveData.observe(viewLifecycleOwner, Observer {
            galleryAdapter.submitList(it)
            swipeRefresh.isRefreshing = false

        })


        recyclyerView.apply {
            adapter = galleryAdapter
//            layoutManager = GridLayoutManager(requireContext(), 2)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        swipeRefresh.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }

        galleryViewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            Log.d("mytag", "onActivityCreated: $it")



        })


        /*

        galleryViewModel.photoListLive.observe(viewLifecycleOwner, Observer {
            if (galleryViewModel.needToScrollToTop) {
                recyclyerView.scrollToPosition(0)
                galleryViewModel.needToScrollToTop = false
            }

            galleryAdapter.submitList(it)
            swipeRefresh.isRefreshing = false
        })

//        galleryViewModel.photoListLive.value ?: galleryViewModel.resetQuery()

        galleryViewModel.dataStatusLive.observe(viewLifecycleOwner, {
            galleryAdapter.footerViewStatus = it
            galleryAdapter.notifyItemChanged(galleryAdapter.itemCount - 1)
            if (it == DATA_STATUS_NETWORK_ERROR) swipeRefresh.isRefreshing = false
        })

        swipeRefresh.setOnRefreshListener {
            galleryViewModel.resetQuery()
        }

        recyclyerView.addOnScrollListener(object :RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                Log.d("mytag", "onScrollStateChanged: "+ newState)
//                // newState
//                // 0 停止滚动
//                // 1 开始滚动
//                // 2 快速滚动
//            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("mytag", "onScrolled: "+ dx + "," + dy)
                if (dy < 0) return

                val layoutManager : StaggeredGridLayoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                val intArray = IntArray(2)
                layoutManager.findLastVisibleItemPositions(intArray)
                if (intArray[0] == galleryAdapter.itemCount - 1) {
                    galleryViewModel.fectchData()
                }
            }
        })

        */

    }

}