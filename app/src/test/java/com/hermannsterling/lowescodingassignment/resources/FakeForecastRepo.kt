package com.hermannsterling.lowescodingassignment.resources

import com.hermannsterling.lowescodingassignment.model.City
import com.hermannsterling.lowescodingassignment.model.Coordinate
import com.hermannsterling.lowescodingassignment.model.Forecast
import com.hermannsterling.lowescodingassignment.model.ForecastItem
import com.hermannsterling.lowescodingassignment.repo.ForecastRepository
import com.hermannsterling.lowescodingassignment.utils.State

class FakeForecastRepo : ForecastRepository {

    companion object {
        private const val DEFAULT_ERROR = "No data found for"
        private const val SERVER_ERROR = "Something went wrong"
    }

    private val cities = listOf(
        "New York",
        "Chicago",
        "New Jersey",
        "Baltimore"
    )

    private val testForecast = Forecast(
        "",
        0,
        0,
        listOf<ForecastItem>(),
        City(
            0,
            "",
            Coordinate(0f, 0f),
            "",
            0,
            0,
            0,
            0
        )
    )

    override suspend fun getWeatherForecast(query: String): State {
        return if (query.isBlank()) State.Error("No query to search")
        else {
            if (cities.contains(query))
                State.Success(testForecast)
            else State.Error(String.format("%s %s", DEFAULT_ERROR, query))

        }
    }

}