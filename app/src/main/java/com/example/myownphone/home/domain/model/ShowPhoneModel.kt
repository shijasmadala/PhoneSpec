package com.example.myownphone.home.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowPhoneModel(
    val detail: String?,
    val image: String?,
    val phoneName: String?,
    val slug: String?,
    var isFavouriteAdded : Boolean = false
) : Parcelable