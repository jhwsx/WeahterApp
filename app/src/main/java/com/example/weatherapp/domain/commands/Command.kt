package com.example.weatherapp.domain.commands

/**
 *
 * @author wzc
 * @date 2019/4/1
 */
interface Command<out T> {
    fun execute() : T
}