package com.example.workmanager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf


class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {


        val name = inputData.getString(INPUT_DATA_KEY)
        Log.d("MyWorkerTag", "doWork: start : " + name)
        Thread.sleep(3000)

        val sps = applicationContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        var number = sps.getInt(name, 0)
        sps.edit().putInt(name, ++number).apply()

        Log.d("MyWorkerTag", "doWork: finish : " + name)
        return Result.success(workDataOf(OUTPUT_DATA_KEY to "$name"))
    }
}