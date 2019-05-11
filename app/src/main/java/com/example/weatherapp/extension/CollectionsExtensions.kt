package com.example.weatherapp.extension

/**
 * Map 的扩展函数：把 map 转换成一个 vararg 数组
 * @author wzc
 * @date 2019/5/11
 */
fun<K,V:Any> Map<K,V?>.toVarargArray(): Array<out Pair<K,V>> =
        map { Pair(it.key, it.value!!) }.toTypedArray()