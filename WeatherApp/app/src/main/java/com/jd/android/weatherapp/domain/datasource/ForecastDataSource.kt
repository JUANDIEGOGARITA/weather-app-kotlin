package com.jd.android.weatherapp.domain.datasource

import com.jd.android.weatherapp.domain.model.Forecast
import com.jd.android.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}