package com.example.weatherapp.domain.datasource

import com.example.weatherapp.data.db.ForecastDb
import com.example.weatherapp.data.server.ForecastServer
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
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
     = sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDatasource, days: Int, zipCode: Long): ForecastList? {
        val result = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (result != null && result.size >= days) result else null
    }


    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}