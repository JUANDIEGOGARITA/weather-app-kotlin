package com.jd.android.weatherapp.domain.datasource

import com.jd.android.weatherapp.domain.model.Forecast
import com.jd.android.weatherapp.domain.model.ForecastList
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class ForecastProviderTest {

    @Test
    fun `data source returns a value`() {
        val ds = mock(ForecastDataSource::class.java)
        whenever(ds.requestDayForecast(0)).then {
            Forecast(0, 0, "desc", 20, 0, "url")
        }
        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))
    }

    @Test
    fun `empty database returns server value`() {
        val db = mock(ForecastDataSource::class.java)

        val server = mock(ForecastDataSource::class.java)
        whenever(server.requestForecastByZipCode(any(Long::class.java), any(Long::class.java)))
            .then {
                ForecastList(0, "city", "country", listOf())
            }

        val provider = ForecastProvider(listOf(db, server))

        assertNotNull(provider.requestByZipCode(0, 0))
    }
}

