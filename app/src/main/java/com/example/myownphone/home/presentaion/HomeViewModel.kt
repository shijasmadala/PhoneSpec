package com.example.myownphone.home.presentaion

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    homeState.value =
                        homeState.value.copy(phones = phones.value, error = "", isLoading = false)
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
}