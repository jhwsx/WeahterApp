package com.example.weatherapp.extension

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 *
 * @author wzc
 * @date 2019/6/1
 */
fun Context.color(res: Int) = ContextCompat.getColor(this, res)