package com.jd.android.weatherapp.data.db

import com.jd.android.weatherapp.domain.mappers.ForecastDataMapper
import com.jd.android.weatherapp.extensions.parseList
import com.jd.android.weatherapp.extensions.parseOpt
import org.jetbrains.anko.db.select

class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: ForecastDataMapper = ForecastDataMapper()) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = {id}" +
                "AND ${DayForecastTable.DATE} >= {date}"

        val dailyForecast = select(DayForecastTable.NAME)
            .whereArgs(dailyRequest, "id" to zipCode, "date" to date)
            .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
            .whereSimple(
                "${CityForecastTable.ID} = ?",
                zipCode.toString()
            )
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }
    }
}