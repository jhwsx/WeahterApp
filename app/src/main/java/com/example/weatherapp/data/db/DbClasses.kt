package com.example.weatherapp.data.db

/**
 * 与数据库中的条目对应的类
 * @author wzc
 * @date 2019/5/10
 */
class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}

class DayForecast(var map: MutableMap<String, Any?>) {
    var _id: Long by map // id 是不需要设置的，它是通过SQLite自增长的
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int, low: Int, iconUrl: String, cityId: Long)
            : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}

/**
 * 总结：
 * 1，通过使用委托，可以把这些属性直接映射到数据库中，也可以从数据库映射出这些属性值。
 * 2，注意的是，属性的名字要和数据库中的名字一模一样，不然无法进行映射的。
 */
