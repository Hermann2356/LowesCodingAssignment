package com.hermannsterling.lowescodingassignment.repo

import com.hermannsterling.lowescodingassignment.BuildConfig
import com.hermannsterling.lowescodingassignment.repo.remote.ForecastService
import com.hermannsterling.lowescodingassignment.utils.State
import javax.inject.Inject

class ForecastRepo @Inject constructor(
    private val weatherService: ForecastService
) : ForecastRepository {

    companion object {
        private const val DEFAULT_ERROR = "No data found for"
        private const val SERVER_ERROR = "Something went wrong"
    }

    override suspend fun getWeatherForecast(
        query: String
    ): State = try {
        if (query.isBlank()) State.Error("No query to search")
        else {
            val response = weatherService.getWeatherForecast(query, BuildConfig.API_KEY, "imperial")
            response.run {
                val data = body()
                if (isSuccessful && data != null) State.Success(data)
                else State.Error(String.format("%s %s", DEFAULT_ERROR, query))
            }
        }
    } catch (e: Exception) {
        State.Error(SERVER_ERROR)
    }
}