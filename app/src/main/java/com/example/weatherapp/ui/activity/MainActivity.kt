package com.example.weatherapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.commands.RequestForecastCommand
import com.example.weatherapp.ui.adapter.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result)
            }
        }
    }
}

/**
 * 总结：
 * 1，doAsync 和 uiThread 是 anko 里的函数，用于简洁地执行任务，并返回主线程。
 */
