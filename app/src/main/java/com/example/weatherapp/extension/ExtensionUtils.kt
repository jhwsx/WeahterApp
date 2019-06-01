package com.example.weatherapp.extension

import java.text.DateFormat
import java.util.*

/**
 *
 * @author wzc
 * @date 2019/6/1
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}