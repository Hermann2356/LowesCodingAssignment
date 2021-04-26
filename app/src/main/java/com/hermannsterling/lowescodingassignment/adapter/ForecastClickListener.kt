package com.hermannsterling.lowescodingassignment.adapter

import com.hermannsterling.lowescodingassignment.model.ForecastItem

interface ForecastClickListener {
    fun onClick(forecast: ForecastItem)
}