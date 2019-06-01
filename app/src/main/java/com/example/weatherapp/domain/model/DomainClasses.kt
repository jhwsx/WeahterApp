package com.example.weatherapp.domain.model


/**
 * 应用展示时需要的数据类
 * @author wzc
 * @date 2019/4/1
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast:List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]
//    fun size() = dailyForecast.size // 省略返回值，让编译器自己去推断，但是只有表达式体函数的返回类型可以省略，代码块体函数则不行
    val size : Int
        get() = dailyForecast.size // 这一种写法和上面的写法，都没有声明一个 size 字段，只有一个 getter 方法而已。不同之处是引用方式的不同，上面的是对象.size(), 下面的是对象.size。
}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)