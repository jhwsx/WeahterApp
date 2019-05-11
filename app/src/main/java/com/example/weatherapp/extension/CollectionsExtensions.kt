package com.example.weatherapp.extension

/**
 * Map 的扩展函数：把 map 转换成一个 vararg 数组
 * @author wzc
 * @date 2019/5/11
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
    map { Pair(it.key, it.value!!) }.toTypedArray()

inline fun <T, R: Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("Collection contains no element matching the predicate.")
}

/**
 * 总结：
 * 1，firstResult 是由 _Collections.kt 中的 first 函数修改而来。这里用了泛型参数。
 */