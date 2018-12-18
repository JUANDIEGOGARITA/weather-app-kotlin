package com.jd.android.weatherapp.domain.commands

import com.jd.android.weatherapp.domain.datasource.ForecastProvider
import com.jd.android.weatherapp.domain.model.ForecastList

class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}