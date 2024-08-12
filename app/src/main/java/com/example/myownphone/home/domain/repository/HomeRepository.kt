package com.example.myownphone.home.domain.repository

import com.example.myownphone.detail.domain.model.dto.PhoneDetailShowModel
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getPhones(category: String): Flow<Resource<List<ShowPhoneModel>>>

    suspend fun searchPhones(query: String): Flow<Resource<List<ShowPhoneModel>>>

    suspend fun getPhoneItemDetails(phoneId: String): Flow<Resource<PhoneDetailShowModel>>

    suspend fun insertPhoneEntity(showPhoneModel: ShowPhoneModel)

    suspend fun getAllPhones() : Flow<List<ShowPhoneModel>>

    suspend fun deletePhoneEntity(showPhoneModel: ShowPhoneModel)
}