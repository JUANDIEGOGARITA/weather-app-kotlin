package com.jd.android.weatherapp.extensions

import android.content.Context
import kotlin.reflect.KProperty

class LongPreference(private val context: Context, private val name: String, val default: Long) {

    private val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long =
        prefs.getLong(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(name, value).apply()
    }
}

class Preference<T>(private val context: Context, private val name: String, private val default: T) {

    private val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        findPreference(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun findPreference(name: String, default: T): T =
        with(prefs) {
            val res: Any = when (default) {
                is Long -> getLong(name, default)
                is String -> getString(name, default)
                is Int -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float -> getFloat(name, default)
                else -> throw IllegalArgumentException("this type can't be saved into preferences")
            }
            res as T
        }

    private fun putPreference(name: String, value: T) =
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float -> putFloat(name, value)
                else -> throw IllegalArgumentException("this type can't be saved into preferences")
            }.apply()
        }
}

object DelegatesExt {

    fun longPreference(context: Context, name: String, default: Long) =
        LongPreference(context, name, default)

    fun <T> preference(context: Context, name: String, default: T) =
        Preference(context, name, default)
}


