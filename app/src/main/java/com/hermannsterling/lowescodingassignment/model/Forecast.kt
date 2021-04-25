package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Forecast(
    val cod: String,
    val message: Int,
    val cnt: Long,
    val list: List<ForecastItem>,
    val city: City,
) : Parcelable