package com.example.weatherapp.data.db

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 * 数据库数据映射类
 * <p>
 * 作用是把数据类映射成对应于数据库中条目的类, 把数据库中条目的类转换成数据类
 * @author wzc
 * @date 2019/5/11
 */
class DbDataMapper {
    /**
     * 从应用数据类转换成数据库条目类
     */
    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    /**
     * 从数据库条目类转换成应用数据类
     */
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }
}