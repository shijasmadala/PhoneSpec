package com.example.myownphone.home.presentaion

import com.example.myownphone.home.domain.model.ShowPhoneModel

data class HomeUiState(
    val phones: List<ShowPhoneModel> = emptyList(),
    val phoneEntityAdded: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
