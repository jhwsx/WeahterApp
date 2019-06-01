package com.example.weatherapp.domain.datasource

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.data.server.ForecastServer
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.extension.firstResult

/**
 *
 * @author wzc
 * @date 2019/5/11
 */
class ForecastProvider(private val sources: List<ForecastDatasource> = ForecastProvider.SOURCES) {
    companion object {
        const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer()) // 注意这里的顺序是先数据库，再 API 查询
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
     = requestToSources { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDatasource, days: Int, zipCode: Long): ForecastList? {
        val result = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (result != null && result.size >= days) result else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T: Any> requestToSources(f: (ForecastDatasource) -> T?): T = sources.firstResult { f(it) }
}