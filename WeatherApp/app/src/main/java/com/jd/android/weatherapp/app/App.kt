package com.jd.android.weatherapp.app

import android.app.Application
import com.jd.android.weatherapp.util.DelegatesExt

class App : Application(){

    companion object {
        var instance: App by
                DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}