package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class City(
    val id: Long,
    val name: String,
    val coord: Coordinate,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
): Parcelable