package com.jd.android.weatherapp.data.db

import com.jd.android.weatherapp.domain.model.ForecastList
import com.jd.android.weatherapp.extensions.clear
import com.jd.android.weatherapp.extensions.parseList
import com.jd.android.weatherapp.extensions.parseOpt
import com.jd.android.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(
    private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    private val dataMapper: DbDataMapper = DbDataMapper()
) {

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

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}