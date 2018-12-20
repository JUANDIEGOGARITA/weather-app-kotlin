package com.jd.android.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jd.android.weatherapp.R
import com.jd.android.weatherapp.domain.model.Forecast
import com.jd.android.weatherapp.domain.model.ForecastList
import kotlinx.android.synthetic.main.item_forecast.*
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer


class ForecastListAdapter(private val weekForecast: ForecastList, private val itemClick: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    class ViewHolder(override val containerView: View, private val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(iconUrl).into(icon)
                dateText.text = date.toString()
                descriptionText.text = description
                maxTemperature.text = "${high}º"
                minTemperature.text = "${low}º"

                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}