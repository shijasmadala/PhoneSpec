package com.example.myownphone.home.data.dto


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Data(
    @Json(name = "phones")
    val phones: List<Phone>,
    @Json(name = "title")
    val title: String
) : Parcelable