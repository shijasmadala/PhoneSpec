package com.example.myownphone.detail.presentation

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
class PhoneDetailsViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {
    var phoneDetailState = mutableStateOf(PhoneDetailsState())
        private set

    fun getPhoneItemDetails(phoneId: String) = viewModelScope.launch {
        homeRepository.getPhoneItemDetails(phoneId).collectLatest { detail ->
            when (detail) {
                Resource.Empty -> {

                }

                is Resource.Error -> {
                    phoneDetailState.value =
                        phoneDetailState.value.copy(isLoading = false, error = detail.error)
                }

                Resource.Loading -> {
                    phoneDetailState.value = phoneDetailState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    phoneDetailState.value =
                        phoneDetailState.value.copy(isLoading = false, phoneDetail = detail.value)
                }
            }
        }
    }
}