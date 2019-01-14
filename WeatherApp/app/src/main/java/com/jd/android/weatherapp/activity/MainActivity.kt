package com.jd.android.weatherapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.jd.android.weatherapp.R
import com.jd.android.weatherapp.adapter.ForecastListAdapter
import com.jd.android.weatherapp.domain.commands.RequestForecastCommand
import com.jd.android.weatherapp.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    private val zipCode: Long by
        DelegatesExt.longPreference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        forecastList.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() =
        doAsync {
            val result = RequestForecastCommand(zipCode).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(
                        DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city
                    )
                }
                forecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country})"
            }
        }
}