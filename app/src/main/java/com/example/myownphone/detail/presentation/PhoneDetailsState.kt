package com.example.myownphone.detail.presentation

import com.example.myownphone.detail.domain.model.dto.PhoneDetailShowModel

data class PhoneDetailsState(
    val phoneDetail: PhoneDetailShowModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
