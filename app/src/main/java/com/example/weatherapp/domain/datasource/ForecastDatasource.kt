package com.example.weatherapp.domain.datasource

import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 * 统一的天气数据接口
 * @author wzc
 * @date 2019/5/11
 */
interface ForecastDatasource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}