package com.jd.android.weatherapp.extensions

import android.content.Context
import kotlin.reflect.KProperty

class LongPreference(private val context: Context, private val name: String, val default: Long) {

    private val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long =
        prefs.getLong(name, default)
}

object DelefatesExt {

    fun longPreference(context: Context, name: String, default: Long) =
        LongPreference(context, name, default)
}


