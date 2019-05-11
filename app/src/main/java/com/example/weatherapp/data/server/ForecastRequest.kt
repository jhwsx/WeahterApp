package com.example.weatherapp.data.server

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * 天气请求类
 * @author wzc
 * @date 2019/4/1
 */
class ForecastRequest(private val zipCode: Long) {
    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.d(javaClass.simpleName, "forecastJsonStr:" + forecastJsonStr)
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}

/**
 * 总结：
 * 1，伴生对象的使用：
 *  在这里使用伴生对象，相当于在 java 类中定义的静态属性、常量或者函数。
 * 2，定义常量的办法：使用 const 关键字，只能用于顶层或者对象内。
 */