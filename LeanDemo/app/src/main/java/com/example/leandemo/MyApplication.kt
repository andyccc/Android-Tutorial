package com.example.leandemo

import android.app.Application
import cn.leancloud.LeanCloud;
import cn.leancloud.LCObject




class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // 提供 this、App ID、App Key、Server Host 作为参数
        // 注意这里千万不要调用 cn.leancloud.core.LeanCloud 的 initialize 方法，否则会出现 NetworkOnMainThread 等错误。
        LeanCloud.initialize(this, "YaKud16hyA4QIyBGJvogUSEq-gzGzoHsz", "MKE9fN9XQc5tcG6KIFk5eQ59", "https://yakud16h.lc-cn-n1-shared.com");


        // 测试代码
//        val testObject = LCObject("TestObject")
//        testObject.put("words", "Hello world!")
//        testObject.saveInBackground().blockingSubscribe()

    }

}