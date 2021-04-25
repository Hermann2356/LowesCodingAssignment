package com.hermannsterling.lowescodingassignment.repo.remote

import com.hermannsterling.lowescodingassignment.model.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast(
        @Query("q") query: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ) : Response<Forecast>
}