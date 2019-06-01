package com.example.weatherapp.extension

import android.database.sqlite.SQLiteDatabase
import com.example.weatherapp.data.db.DayForecastTable
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 *
 * @author wzc
 * @date 2019/5/11
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> = // 这里传入的是一个函数类型，使用的是一个 lambda 表达式
    parseList(object : MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T = parser(columns) // 决定了如何解析
    })

fun <T: Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T):T? =
        parseOpt(object: MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun SelectQueryBuilder.byId(id: Long) = whereSimple("${DayForecastTable.ID} = ?", id.toString())

/**
 * 总结：
 * 1，parseList 是用来解析 0 行或者多行，而 parseOpt 是用来解析 0 行或者 1 行的，parseSingle 是用来解析正好 1 行的。
 * 对于 parseOpt 来说，如果行数大于1，就会抛出异常；对于 parseSingle 来说，如果行数不等于 1，就会抛出异常。
 * 2，RowParser 和 MapRowParser 的区别是什么？
 * 使用 RowParser 必须知道每一列的索引；MapRowParser 可以通过使用列的名字来获取值。
 */