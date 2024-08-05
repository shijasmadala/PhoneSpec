package com.example.myownphone.favorite.presentation

import com.example.myownphone.home.domain.model.ShowPhoneModel

sealed class FavoriteScreenEvent {
    data class OnDeleteFavoriteItem(val phone: ShowPhoneModel) :
        FavoriteScreenEvent()
}