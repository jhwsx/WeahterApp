package com.example.weatherapp.extension

import android.content.Context
import android.view.View

/**
 *
 * @author wzc
 * @date 2019\4\3
 */
// 扩展属性的用法：必须定义 get() 方法。
val View.ctx: Context
    get() = context
