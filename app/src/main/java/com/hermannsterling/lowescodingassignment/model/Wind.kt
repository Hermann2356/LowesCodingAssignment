package com.hermannsterling.lowescodingassignment.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Wind(
    val speed: Float,
    val deg: Int
) : Parcelable