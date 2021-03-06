package com.example.weatherapp.data.server

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.domain.datasource.ForecastDatasource
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList

/**
 *
 * @author wzc
 * @date 2019/5/11
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()):ForecastDatasource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()
}

/**
 * 总结：
 * 1，在 kotlin 中，几乎一切都是表达式
 */