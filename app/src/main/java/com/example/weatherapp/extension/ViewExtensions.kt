package com.example.weatherapp.extension

import android.content.Context
import android.view.View
import android.widget.TextView

/**
 *
 * @author wzc
 * @date 2019\4\3
 */
// 扩展属性的用法：必须定义 get() 方法。
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) {
        setTextColor(value)
    }

fun View.slideExit() {
    if (translationY == 0F) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}
