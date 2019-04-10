package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.Forecast
import com.example.weatherapp.data.ForecastResult
import com.example.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.weatherapp.domain.model.Forecast as ModelForecast

/**
 *
 * @author wzc
 * @date 2019/4/1
 */
class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList =
        ForecastList(
            forecast.city.name,
            forecast.city.country,
            convertForecastListToDomain(forecast.list)
        )

    /**
     * 把 json 中的 List<Forecast> 转成 model 的 List<Forecast>
     */
    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed{ i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    /**
     * 把 json 对象 Forecast 转成 model 下的 Forecast
     */
    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(
            convertDate(forecast.dt), forecast.weather[0].description,
            forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon)
        )
    }

    /**
     * 转换日期格式
     */
    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"


}

/**
 * 总结：
 * 1，这里用了数据类的 copy 函数，可以 copy 一个对象，改变指定的属性值。
 * 2，把大步骤拆成小步骤的代码风格。
 */