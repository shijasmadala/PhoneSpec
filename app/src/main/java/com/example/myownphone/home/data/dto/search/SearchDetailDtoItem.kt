package com.example.myownphone.home.data.dto.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchDetailDtoItem(
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "_id")
    val id: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "phone_name")
    val phoneName: String?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "__v")
    val v: Int?
) : Parcelable