package com.example.myownphone.detail.domain.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhoneDetailShowModel(
    val brand: String?,
    val phoneName: String?,
    val thumbnail: String?,
    val releaseDate: String?,
    val os: String?,
    val dimension: String?,
    val storage: String?,
    val imageLists: List<String>?,
    val specification: List<ShowSpecification>?
) : Parcelable
