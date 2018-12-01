package com.jd.android.weatherapp.domain.commands

public interface Command<out T> {
    fun execute(): T
}