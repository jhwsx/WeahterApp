package com.example.weatherapp.ui.util

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *
 * @author wzc
 * @date 2019/4/24
 */
object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}

class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
        // getter 函数 如果已经被初始化，则会返回一个值，否则抛出异常
        value ?: throw IllegalStateException("${property.name} not initialized")

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        // setter 函数 如果值仍然是 null，则进行赋值；否则抛出异常
        this.value = if (this.value == null) value else
            throw IllegalStateException("${property.name} already initialized")
    }
}

/**
 * 总结：
 * 1，示例中的代码为什么是下边这样的？
 *
 * class NotNullSingleValueVar<T> {

private var value: T? = null

operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
value ?: throw IllegalStateException("${property.name} not initialized")

operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
this.value = if (this.value == null) value
else throw IllegalStateException("${property.name} already initialized")
}
}
 * 2, 这里是一个自定义的委托：
 * val 属性实现 ReadOnlyProperty 接口，var 属性实现 ReadWriteProperty 接口。
 * 3, 当使用 instance 的 setter 和 getter 的时候，属性委托的 setValue 和 getValue 就会被调用
 * */