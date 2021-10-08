package com.example.gallery

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

// 构造器私有化
class VolleySingleton private constructor(context: Context) {
    companion object { // 静态的
        private var instance: VolleySingleton? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                VolleySingleton(context).also { instance = it }
            }


    }

    val requireQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }


}