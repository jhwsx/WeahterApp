package com.example.weatherapp.data.db

/**
 * 数据库表字段类
 * @author wzc
 * @date 2019/5/6
 */
/**
 * 城市天气表
 */
object CityForecastTable {
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

/**
 * 某天天气表
 */
object DayForecastTable {
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}

/**
 * 总结：
 * 1，这里使用 object 关键字作对象声明，相当于是一个单例。
 * 2，但是，我觉得这里应该是用常量才对。有疑问？
 */