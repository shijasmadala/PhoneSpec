package com.example.myownphone.favorite.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {
    var favoriteScreenState = mutableStateOf(FavoriteState())
        private set

    init {
        getAllPhone()
    }

    fun onEvent(favoriteScreenEvent: FavoriteScreenEvent) {
        when(favoriteScreenEvent){
            is FavoriteScreenEvent.OnDeleteFavoriteItem -> {
                deletePhoneEntity(favoriteScreenEvent.phone)
            }
        }
    }

    private fun getAllPhone() = viewModelScope.launch {
        homeRepository.getAllPhones().collectLatest {
           favoriteScreenState.value = favoriteScreenState.value.copy(phones = it)
        }
    }

    fun insertPhoneEntity(showPhoneModel: ShowPhoneModel) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            homeRepository.insertPhoneEntity(showPhoneModel)
        }.onSuccess {
            favoriteScreenState.value = favoriteScreenState.value.copy(message = "Phone Added")
        }.onFailure {
            favoriteScreenState.value = favoriteScreenState.value.copy(error = "error")
        }
    }

    fun deletePhoneEntity(showPhoneModel: ShowPhoneModel) = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            homeRepository.deletePhoneEntity(showPhoneModel)
        }.onSuccess {
            favoriteScreenState.value = favoriteScreenState.value.copy(message = "phone deleted")
        }.onFailure {
            favoriteScreenState.value = favoriteScreenState.value.copy(error = "error")
        }
    }
}