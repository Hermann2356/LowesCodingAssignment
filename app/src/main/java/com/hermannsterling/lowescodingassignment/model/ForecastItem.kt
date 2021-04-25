package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ForecastItem (
    val dt: Long,
    val main : MainForecast,
    val weather: List<Weather>,
    val clouds: Cloud,
    val wind: Wind,
    val visibility: Int,
    val pop: Float,
    val sys: Sys,
    val dt_txt: String
) : Parcelable