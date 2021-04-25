package com.hermannsterling.lowescodingassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hermannsterling.lowescodingassignment.databinding.ItemForecastBinding
import com.hermannsterling.lowescodingassignment.model.ForecastItem

class ForecastAdapter(
    private val forecastList: List<ForecastItem>,
    private val listener: ForecastClickListener
) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
        ItemForecastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener
    )


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.run {
        val forecast = forecastList[position]
        bind(forecast)
        onClick(forecast)
    }

    override fun getItemCount(): Int = forecastList.size

    class ForecastViewHolder(
        private val binding: ItemForecastBinding,
        private val listener: ForecastClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ForecastItem) {
            binding.apply {
                tvWeatherDescription.text = forecast.weather[0].main
                tvWeatherTemp.text = String.format("Temp: %d", forecast.main.temp.toInt())
            }
        }

        fun onClick(forecast: ForecastItem) {
            binding.root.setOnClickListener {
                listener.onClick(forecast)
            }
        }
    }

}