package com.example.myownphone.detail.domain.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowSpec(
    val keySpec : String?,
   val keyDetail: List<String>?
):Parcelable
