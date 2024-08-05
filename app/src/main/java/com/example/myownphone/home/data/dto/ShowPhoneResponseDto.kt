package com.example.myownphone.home.data.dto


import android.os.Parcelable
import com.example.myphone.home.data.dto.Data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ShowPhoneResponseDto(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "status")
    val status: Boolean
) : Parcelable