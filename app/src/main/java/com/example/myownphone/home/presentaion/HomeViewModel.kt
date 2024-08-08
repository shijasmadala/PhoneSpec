package com.example.myownphone.home.presentaion

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.home.domain.repository.HomeRepository
import com.example.myownphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    var homeState = mutableStateOf(HomeUiState())
        private set

    init {
        getLatestPhone()
    }

    fun getLatestPhone() = viewModelScope.launch {
        homeRepository.getPhones().collectLatest { phones ->
            when (phones) {
                Resource.Empty -> {}
                is Resource.Error -> {
                    homeState.value = homeState.value.copy(error = phones.error, isLoading = false)
                }

                Resource.Loading -> {
                    homeState.value = homeState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    getLocalPhones(phones.value)
                }
            }
        }
    }

    fun searchPhone(query: String) = viewModelScope.launch {
        homeRepository.searchPhones(query).collectLatest { searchItems ->
            when (searchItems) {
                Resource.Empty -> {}
                is Resource.Error -> {
                    homeState.value =
                        homeState.value.copy(error = searchItems.error, isLoading = false)
                }

                Resource.Loading -> {
                    homeState.value = homeState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    homeState.value = homeState.value.copy(
                        isLoading = false,
                        phones = searchItems.value
                    )

                }
            }
        }
    }

    private fun getLocalPhones(onlinePhones: List<ShowPhoneModel>) = viewModelScope.launch {
        homeRepository.getAllPhones().collectLatest { phonesFromLocal ->
            filterPhoneIsAddedFavorite(localPhones = phonesFromLocal, onlinePhones = onlinePhones)
        }
    }

    private fun filterPhoneIsAddedFavorite(
        localPhones: List<ShowPhoneModel>?,
        onlinePhones: List<ShowPhoneModel>?
    ) {
        val onlinePhonesMap = onlinePhones?.associateBy { it.slug }

        localPhones?.filter { it.isFavouriteAdded }?.forEach { localPhone ->
            onlinePhonesMap?.get(localPhone.slug)?.isFavouriteAdded = true
        }
        homeState.value =
            homeState.value.copy(
                phones = onlinePhones ?: emptyList(),
                error = "",
                isLoading = false
            )
    }

    fun toggleFavorite(selectedPhone: ShowPhoneModel) {
         homeState.value.phones.map {
            if (it.slug == selectedPhone.slug) {
                it.isFavouriteAdded = !it.isFavouriteAdded
            } else {
                it
            }
        }
    }
}