package com.example.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.weatherapp.domain.model.ForecastList

/**
 *
 * @author wzc
 * @date 2019/3/27
 */
class ForecastListAdapter (private val weekForecast: ForecastList) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = weekForecast.size()


    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
        with(weekForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }

    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}

/**
 * 总结：
 * 1，with 函数的使用：
 * with 函数接收两个参数，第一个参数是 lambda 的接收者，第二个参数是 lambda；
 * 在 lambda 可以显式地通过 this 来访问 lambda 的接收者，调用它的方法和属性，也可以把 this 省略掉；
 * with 函数的返回值是执行 lambda 代码的结果。
 * 2，这个类的主构造方法省略了 constructor 关键字，因为它没有注解或可见性修饰符。
 */