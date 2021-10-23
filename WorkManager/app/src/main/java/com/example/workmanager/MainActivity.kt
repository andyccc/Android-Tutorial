package com.example.workmanager

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

const val INPUT_DATA_KEY = "input_data_key"
const val OUTPUT_DATA_KEY = "output_data_key"

const val WOKR_A_NAME = "wokr_a"
const val WOKR_B_NAME = "wokr_b"

const val SHARED_PREFERENCES_NAME = "sps_name"

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private val workManager = WorkManager.getInstance(this)

    /*
    1、back 退出应用, work 会继续执行
    2、最近引用杀掉, work 还是会继续执行
    3、设置中，应用列表里的强制停止，下次应用启动后 work 会继续执行
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).registerOnSharedPreferenceChangeListener(this)
        updateView()
//        test1()
        test2()


    }

    fun updateView() {
        val sps = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        textViewA.text = sps.getInt(WOKR_A_NAME, 0).toString()

        textViewB.text = sps.getInt(WOKR_B_NAME, 0).toString()

    }

    fun test2() {
        button.setOnClickListener {
            val workRequestA = createWork(WOKR_A_NAME)
            val workRequestB = createWork(WOKR_B_NAME)
            workManager.beginWith(workRequestA)
                .then(workRequestB)
                .enqueue()

//            workManager.getWorkInfoByIdLiveData(workRequestA.id).observe(this, Observer {
//                Log.d("MyWorkerTag", "onCreate: ${it.state}")
//                if (it.state == WorkInfo.State.SUCCEEDED) {
//                    Log.d("MyWorkerTag", "onCreate2: ${it.outputData.getString(OUTPUT_DATA_KEY)}")
//                }
//            })
        }

    }

    fun test1() {
        button.setOnClickListener {
            val workRequestA = createWork(WOKR_A_NAME)
            workManager.enqueue(workRequestA)
            workManager.getWorkInfoByIdLiveData(workRequestA.id).observe(this, Observer {
                Log.d("MyWorkerTag", "onCreate: ${it.state}")
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    Log.d("MyWorkerTag", "onCreate2: ${it.outputData.getString(OUTPUT_DATA_KEY)}")
                }
            })
        }

    }

    private fun createWork(name: String): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            //                .setConstraints(constraints)
            .setInputData(workDataOf(INPUT_DATA_KEY to name))
            //                .setInputData(workDataOf(Pair(INPUT_DATA_KEY,WOKR_A_NAME )))
            .build()
        return workRequest
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        updateView()
    }
}