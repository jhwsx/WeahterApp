package com.example.weatherapp.domain.commands

import com.example.weatherapp.domain.datasource.ForecastProvider
import com.example.weatherapp.domain.model.Forecast

/**
 *
 * @author wzc
 * @date 2019/6/1
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastProvider = ForecastProvider()):
    Command<Forecast> {
    override fun execute(): Forecast = forecastProvider.requestForecast(id)
}