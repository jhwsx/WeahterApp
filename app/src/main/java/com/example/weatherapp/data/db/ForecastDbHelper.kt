package com.example.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.weatherapp.ui.App
import org.jetbrains.anko.db.*

/**
 * 天气数据库辅助类，负责数据库的创建和升级
 * @author wzc
 * @date 2019/5/6
 */
class ForecastDbHelper(
    ctx: Context = App.instance
) : ManagedSQLiteOpenHelper(
    ctx,
    ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION
) {

    companion object {
        const val DB_NAME = "forecast.db"
        const val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            CityForecastTable.NAME, true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY, // to 是中缀调用，+ 是重载运算符
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT
        )
        db.createTable(
            DayForecastTable.NAME, true,
            DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DayForecastTable.DATE to INTEGER,
            DayForecastTable.DESCRIPTION to TEXT,
            DayForecastTable.HIGH to INTEGER,
            DayForecastTable.LOW to INTEGER,
            DayForecastTable.ICON_URL to TEXT,
            DayForecastTable.CITY_ID to INTEGER
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }

}

/**
 * 总结：
 * 1，这里用了 anko 里的数据库 api，来创建表
 */