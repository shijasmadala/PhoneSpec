package com.example.myownphone.top_by.presentation

import com.example.myownphone.home.domain.model.ShowPhoneModel

data class TopByScreenState(
    val topByPhones: List<ShowPhoneModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val message: String? = null
)