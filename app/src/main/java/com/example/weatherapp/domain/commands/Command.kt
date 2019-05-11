package com.example.weatherapp.domain.commands

/**
 * 指挥接口
 * @author wzc
 * @date 2019/4/1
 */
interface Command<out T> {
    fun execute() : T
}