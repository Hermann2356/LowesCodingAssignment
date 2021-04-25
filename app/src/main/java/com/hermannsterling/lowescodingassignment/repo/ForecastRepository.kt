package com.hermannsterling.lowescodingassignment.repo

import com.hermannsterling.lowescodingassignment.utils.State

interface ForecastRepository {
    suspend fun getWeatherForecast(query: String) : State
}