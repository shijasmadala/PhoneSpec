package com.example.myownphone.top_by.presentation

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
class TopByFansViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    var topByState = mutableStateOf(TopByScreenState())
        private set

    fun getTopByPhone(category: String) = viewModelScope.launch {
        homeRepository.getPhones(category).collectLatest { phones ->
            when (phones) {
                Resource.Empty -> {}
                is Resource.Error -> {
                    topByState.value =
                        topByState.value.copy(error = phones.error, isLoading = false)
                }

                Resource.Loading -> {
                    topByState.value = topByState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    topByState.value = topByState.value.copy(topByPhones = phones.value)
                }
            }
        }
    }
}