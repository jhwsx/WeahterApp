package com.example.weatherapp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.domain.commands.RequestForecastCommand
import com.example.weatherapp.ui.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.* // 这里使用了 kotlin Android extensions
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this) // 布局中的 id 也使用驼峰命名，这样外部的 java 类名才会符合规范。
        doAsync {
            val result = RequestForecastCommand(94043L).execute()
            uiThread {
                //                forecastList.adapter = ForecastListAdapter(result,
//                    // 使用 object 关键字声明一个匿名对象，这里是一个接口，后面没有小括号
//                    object: ForecastListAdapter.OnItemClickListener {
//                        override fun invoke(forecast: Forecast) {
//                            toast(forecast.date)
//                        }
//                    })
                forecastList.adapter =
                    ForecastListAdapter(result) {  toast(it.description) }
            }
        }
//        val button = find<Button>(R.id.button)
//        button.setOnClickListener({view -> toast("hello")})
//        button.setOnClickListener({toast("hello")})
//        button.setOnClickListener {toast("hello kotlin1")}
    }
}

/**
 * 总结：
 * 1，doAsync 和 uiThread 是 anko 里的函数，用于简洁地执行任务，并返回主线程。
 */
