package com.example.myownphone.detail.domain.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowSpecification(
    val title: String?,
    val showSpecs: List<ShowSpec>?
) : Parcelable
