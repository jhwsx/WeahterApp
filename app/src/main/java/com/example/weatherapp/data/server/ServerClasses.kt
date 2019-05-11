package com.example.weatherapp.data.server

/**
 * json 对应的对象类
 * @author wzc
 * @date 2019/3/30
 */
/**
 * 温度，属性名和 json 中完全一致
 */
data class Temperature(val day: Float, val eve: Float, val max: Float, val min: Float,val morn: Float, val night: Float)

/**
 * 天气
 */
data class Weather(val id: Long, val description: String, val icon: String, val main: String)
/**
 * 预报
 */
data class Forecast(val dt: Long, val clouds: Int, val deg: Int, val humidity: Int, val pressure: Float, val speed: Float,
                    val temp: Temperature, val weather: List<Weather>, val rain: Float)

/**
 * 坐标
 */
data class Coordinate(val lat: Float, val lon: Float)
/**
 * 城市
 */
data class City(val id: Long, val name: String,val country: String, val popuplation: Int, val coord: Coordinate)
/**
 * 预报结果
 */
data class ForecastResult(val city: City, val list: List<Forecast>)



