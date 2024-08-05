package com.example.myownphone.favorite.presentation

import com.example.myownphone.home.domain.model.ShowPhoneModel

data class FavoriteState(
    val phones: List<ShowPhoneModel>? = null,
    val message: String? = null,
    val error: String? = null
)
