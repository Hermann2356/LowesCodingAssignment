package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Coordinate(
    val lat: Float,
    val lon: Float
) : Parcelable
