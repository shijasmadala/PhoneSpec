package com.example.myphone.home.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.myownphone.home.data.dto.Phone

@JsonClass(generateAdapter = true)
@Parcelize
data class Data(
    @Json(name = "phones")
    val phones: List<Phone>,
    @Json(name = "title")
    val title: String
) : Parcelable