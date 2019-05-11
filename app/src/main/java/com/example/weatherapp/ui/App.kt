package com.example.weatherapp.ui

import android.app.Application
import com.example.weatherapp.extension.DelegatesExt

/**
 *
 * @author wzc
 * @date 2019/4/13
 */
class App : Application() {
    companion object {
//        private var instance: Application? = null
//        fun instance() = instance!!
        // 使用委托属性
        var instance: App by DelegatesExt.notNullSingleValue()
//        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

/**
 * 总结：
 * 1，伴生对象是在类中创建的，伴生对象的成员属性替代了伴生对象所在类的静态成员，
 * 伴生对象的成员方法还是自己的成员方法。
 * 2, 伴生对象是在类中创建的一个单例。
 * 3，!! 是 非空断言，如果 instance 不是 null，那么还返回自己，如果为空，就抛出 NPE 。
 * 4，by 关键字，用来指定一个委托对象。
 */