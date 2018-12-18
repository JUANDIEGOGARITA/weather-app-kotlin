package com.jd.android.weatherapp.domain.commands

import com.jd.android.weatherapp.domain.datasource.ForecastProvider
import com.jd.android.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(
    val id: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}