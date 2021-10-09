package com.example.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive : LiveData<List<PhotoItem>>
    get() = _photoListLive

    fun fectchData() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("stringRequestTag", it.toString())
            }
        )

        VolleySingleton.getInstance(getApplication()).requireQueue.add(stringRequest)
    }

    private fun getUrl(): String {
        val url = "https://pixabay.com/api/?key=23746904-f6213ab4af145089d76127203&image_type=photo&pretty=true&q=${keyWords.random()}&per_page=100"
        return url
    }

    private val keyWords = arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal")

}

