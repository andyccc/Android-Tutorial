package com.example.kotlindemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _number: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().also { it.value = 0 }
    }

    val number: LiveData<Int>
    get() = _number

//    val number = MutableLiveData(0)

//    init {
//        number = MutableLiveData<Int>()
//        number.value = 0
//    }

    fun modifyNumber(n: Int) {
        _number.value = _number.value?.plus(n)
//        number.value = number.value!! + n // 强制执行 容易崩溃
    }

}