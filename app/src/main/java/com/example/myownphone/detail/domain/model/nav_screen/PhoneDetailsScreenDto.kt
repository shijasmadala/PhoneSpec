package com.example.myownphone.detail.domain.model.nav_screen

import kotlinx.serialization.Serializable

@Serializable
data class PhoneDetailsScreenDto(
    val detail: String?,
    val image: String?,
    val phoneName: String?,
    val slug: String?
)
