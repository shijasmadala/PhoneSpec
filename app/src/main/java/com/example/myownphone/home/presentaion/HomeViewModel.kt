package com.example.myownphone.home.presentaion

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.home.domain.repository.HomeRepository
import com.example.myownphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
        homeRepository.getPhones("latest").collectLatest { phones ->
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

        homeState.value =
            homeState.value.copy(
                phones = onlinePhones,
                error = "",
                isLoading = false
            )
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

    private fun insertPhoneEntity(showPhoneModel: ShowPhoneModel) =
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                homeRepository.insertPhoneEntity(showPhoneModel)
            }.onSuccess {
                homeState.value = homeState.value.copy(message = "Phone Added")
            }.onFailure {
                homeState.value = homeState.value.copy(error = "error")
            }
        }

    private fun deletePhoneEntity(showPhoneModel: ShowPhoneModel) =
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                homeRepository.deletePhoneEntity(showPhoneModel)
            }.onSuccess {
                homeState.value = homeState.value.copy(message = "phone deleted")
            }.onFailure {
                homeState.value = homeState.value.copy(error = "error")
            }
        }

    fun toggleFavorite(selectedPhone: ShowPhoneModel) {
        val updatedPhones = homeState.value.phones.map { phone ->
            if (phone.slug == selectedPhone.slug) {
                phone.copy(isFavouriteAdded = !phone.isFavouriteAdded)
            } else {
                phone
            }
        }

        //for update the data base
        val isFavoriteAdded = !selectedPhone.isFavouriteAdded
        val updatedPhone = selectedPhone.copy(isFavouriteAdded = isFavoriteAdded)


        if (isFavoriteAdded) {
            insertPhoneEntity(updatedPhone)
        } else {
            deletePhoneEntity(updatedPhone)
        }

        //for update the ui
        homeState.value = homeState.value.copy(phones = updatedPhones)
    }
}