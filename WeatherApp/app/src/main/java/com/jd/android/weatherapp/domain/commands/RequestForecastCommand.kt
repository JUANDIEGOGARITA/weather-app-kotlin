package com.jd.android.weatherapp.domain.commands

import com.jd.android.weatherapp.data.ForecastRequest
import com.jd.android.weatherapp.domain.mappers.ForecastDataMapper
import com.jd.android.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)

        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}