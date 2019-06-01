package com.example.weatherapp.data.db

import com.example.weatherapp.domain.datasource.ForecastDatasource
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.extension.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 *
 * @author wzc
 * @date 2019/5/11
 */
class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDatasource { // 这里使用默认参数的方式，实现简单的依赖注入
    /**
     * 根据 zipCode，从数据库中获取 ForecastList 对象
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, zipCode.toString(), date.toString())
            .parseList {it: Map<String, Any?> -> DayForecast(HashMap(it)) } // 这里使用 HashMap 的作用是把解析器接收的 immutable map 转换成了一个 mutable map。而 DayForecast 需要的正是一个 mutable map
        val city = select(CityForecastTable.NAME) // 注意：这里返回的是一个可空类型
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        if (city != null)
            dataMapper.convertToDomain(city)
        else null
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id)
            .parseOpt {DayForecast(HashMap(it))}
        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

    /**
     * 把 ForecastList 对象存入数据库中
     */
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray()) // 第二个参数是 vararg 修饰的 Pair 对象
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}
/**
 * 总结：
 * 1，*map.toVarargArray() 中的 *，表示这个 array 会被分解为一个 vararg 参数。这个在 java 中是自动处理的，但是在 kotlin 中需要明确指明。
 */