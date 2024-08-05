package com.example.myownphone.home.data.dto


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Phone(
    @Json(name = "detail")
    val detail: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "phone_name")
    val phoneName: String,
    @Json(name = "slug")
    val slug: String
) : Parcelable