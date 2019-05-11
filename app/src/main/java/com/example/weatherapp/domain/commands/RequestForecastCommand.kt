package com.example.weatherapp.domain.commands

import com.example.weatherapp.data.server.ForecastRequest
import com.example.weatherapp.domain.mappers.ForecastDataMapper
import com.example.weatherapp.domain.model.ForecastList

/**
 * 请求天气指挥类
 * @author wzc
 * @date 2019/4/1
 */
class RequestForecastCommand(private val zipCode: Long): Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}