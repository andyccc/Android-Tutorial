package com.example.pagergallery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

enum class NetworkStatus {
    INITIAL_LOADING,
    LOADING,
    LOADED,
    FAILED,
    COMPLETED
}


class PixabayDataSource(private val  context: Context): PageKeyedDataSource<Int, PhotoItem>() {
//    companion object {
//        const val LOADING = 1
//        const val FAILED = 2
//        const val COMPLETED = 3
//    }

    var retry : (()->Any)? = null

    private val _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus : LiveData<NetworkStatus> get() = _networkStatus

    private val queryKey = arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal").random()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PhotoItem>
    ) {
        retry = null

        _networkStatus.postValue(NetworkStatus.INITIAL_LOADING)

        val  url = "https://pixabay.com/api/?key=23746904-f6213ab4af145089d76127203&image_type=photo&pretty=true&q=${queryKey}&per_page=50&page=1"
        StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                val dataList = Gson().fromJson(it, Pixabay::class.java).hits.toList()
                callback.onResult(dataList, null, 2)
                _networkStatus.postValue(NetworkStatus.LOADED)
            },
            Response.ErrorListener {
                retry = {loadInitial(params, callback)}
                Log.d("errtag1", "loadInitial: $it")
                _networkStatus.postValue(NetworkStatus.FAILED)

            }
        ).also {
            VolleySingleton.getInstance(context).requireQueue.add(it)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {
        retry = null

        _networkStatus.postValue(NetworkStatus.LOADING)

        val  url = "https://pixabay.com/api/?key=23746904-f6213ab4af145089d76127203&image_type=photo&pretty=true&q=${queryKey}&per_page=50&page=${params.key}"

        StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                val dataList = Gson().fromJson(it, Pixabay::class.java).hits.toList()
                callback.onResult(dataList, params.key + 1)
                _networkStatus.postValue(NetworkStatus.LOADED)
            },
            Response.ErrorListener {
                if (it.toString() == "com.android.volley.ClientError") {
                    _networkStatus.postValue(NetworkStatus.COMPLETED)
                } else {
                    retry = {loadAfter(params, callback)}
                    _networkStatus.postValue(NetworkStatus.FAILED)
                }

                Log.d("errtag1", "loadInitial: $it")
            }
        ).also {
            VolleySingleton.getInstance(context).requireQueue.add(it)
            _networkStatus.postValue(NetworkStatus.FAILED)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {


    }


}