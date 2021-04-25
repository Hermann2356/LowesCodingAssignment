package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable
