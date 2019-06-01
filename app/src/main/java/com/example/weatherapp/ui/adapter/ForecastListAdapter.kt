package com.example.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.extension.ctx
import com.example.weatherapp.extension.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 *
 * @author wzc
 * @date 2019/3/27
 */
class ForecastListAdapter(private val weekForecast: ForecastList,
                          val itemClick: (Forecast) -> Unit) : // itemClick 是一个函数，它接收一个 Forecast 对象，但不返回任何东西。括号里边是参数，箭头右边是返回值。
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent,false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount() = weekForecast.size


    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
       holder.bindForecast(weekForecast[position])
    }
    // view 并不是一个属性，只是一个传参，所以前面没有 var，val
    class ViewHolder(view: View,  val itemClick: (Forecast) -> Unit) :
        RecyclerView.ViewHolder(view) {
//        private val iconView: ImageView
//        private val dateView: TextView
//        private val descriptionView: TextView
//        private val maxTemperatureView: TextView
//        private val minTemperatureView: TextView
        // init 代码块是为主构造器服务的，因为主构造器不能包含任何初始化语句
//        init {
//            iconView = itemView.find(R.id.icon)
//            dateView = itemView.find(R.id.date)
//            descriptionView = itemView.find(R.id.description)
//            maxTemperatureView = itemView.find(R.id.maxTemperature)
//            minTemperatureView = itemView.find(R.id.minTemperature)
//        }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
//                dateView.text = date
//                descriptionView.text = description
//                maxTemperatureView.text = high.toString()
//                minTemperatureView.text = low.toString()
//                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                // 使用 kotlin android extensions，省去了 findViewById 的操作。
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = high.toString()
                itemView.minTemperature.text = low.toString()
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

//    interface OnItemClickListener {
//        // 函数调用 方法 a(i)，会调用的是 a.invoke(i), 也就是说可以使用一种简化的方式
//        operator fun invoke(forecast: Forecast)
//    }
}

/**
 * 总结：
 * 1，with 函数的使用：
 * with 函数接收两个参数，第一个参数是 lambda 的接收者，第二个参数是 lambda；
 * 在 lambda 可以显式地通过 this 来访问 lambda 的接收者，调用它的方法和属性，也可以把 this 省略掉；
 * with 函数的返回值是执行 lambda 代码的结果。
 * 2，这个类的主构造方法省略了 constructor 关键字，因为它没有注解或可见性修饰符。
 */