package com.example.leandemo

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.leancloud.LCException
import cn.leancloud.LCObject
import cn.leancloud.LCQuery
import cn.leancloud.LCUser
import cn.leancloud.livequery.LCLiveQuery
import cn.leancloud.livequery.LCLiveQueryEventHandler
import cn.leancloud.livequery.LCLiveQuerySubscribeCallback
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val _dataListLive = MutableLiveData<List<LCObject>>()

    val dataListLive: LiveData<List<LCObject>> = _dataListLive

    init {
        val query = LCQuery<LCObject>("Word")
        query.whereEqualTo("user", LCUser.getCurrentUser())
        query.findInBackground().subscribe(object : Observer<List<LCObject>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<LCObject>) {
                _dataListLive.value = t
            }

            override fun onError(e: Throwable) {
                Toast.makeText(application, "${e.message}", Toast.LENGTH_SHORT).show()

            }

            override fun onComplete() {

            }

        })

        val liveQuery = LCLiveQuery.initWithQuery(query)
        liveQuery.subscribeInBackground(object : LCLiveQuerySubscribeCallback() {
            override fun done(e: LCException?) {

            }
        })
        liveQuery.setEventHandler(object : LCLiveQueryEventHandler() {
            override fun onObjectCreated(obj: LCObject?) {
                super.onObjectCreated(obj)

                val t = _dataListLive.value?.toMutableList()
                obj?.let { t?.add(it) }
                _dataListLive.value = t
            }

            override fun onObjectUpdated(obj: LCObject?, updateKeyList: MutableList<String>?) {
                super.onObjectUpdated(obj, updateKeyList)

                val ob = _dataListLive.value?.find {
                    it.get("objectId") == obj?.get("objectId")
                }
                updateKeyList?.forEach {
                    ob?.put(it, obj?.get(it))
                }
                _dataListLive.value = _dataListLive.value
            }

            override fun onObjectDeleted(objectId: String?) {
                super.onObjectDeleted(objectId)

                val t = _dataListLive.value?.toMutableList()
                val ob = t?.find { it.get("objectId") == objectId }
                t?.remove(ob)
                _dataListLive.value = t

            }
        })

    }

    fun addWord(newWord: String) {
        LCObject("Word").apply {
            put("word", newWord)
            put("user", LCUser.getCurrentUser())
            saveInBackground().subscribe(object : Observer<LCObject> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: LCObject) {
                    Toast.makeText(getApplication(), "添加成功", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(getApplication(), "${e.message}", Toast.LENGTH_SHORT).show()

                }

                override fun onComplete() {

                }

            })
        }
    }


}