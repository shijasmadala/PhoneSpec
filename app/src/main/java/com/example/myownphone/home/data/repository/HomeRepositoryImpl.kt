package com.example.myownphone.home.data.repository

import com.example.myownphone.detail.domain.model.dto.PhoneDetailShowModel
import com.example.myownphone.home.data.source.HomeService
import com.example.myownphone.home.data.source.PhoneDao
import com.example.myownphone.home.data.source.SearchService
import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.home.domain.repository.HomeRepository
import com.example.myownphone.home.toPhoneEntity
import com.example.myownphone.home.toShowPhoneModel
import com.example.myownphone.utils.Constants
import com.example.myownphone.utils.Resource
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val searchService: SearchService,
    private val phoneDao: PhoneDao
) :
    HomeRepository {
    override suspend fun getPhones(category: String): Flow<Resource<List<ShowPhoneModel>>> = flow {
        emit(Resource.Loading)
        homeService.getLatestPhones(category).suspendOnSuccess {
            val response = this.data
            if (response.status && response != null) {
                val phoneData = response.data.phones.map { it.toShowPhoneModel() }
                emit(Resource.Success(phoneData))
            }
        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }
    }

    override suspend fun searchPhones(query: String): Flow<Resource<List<ShowPhoneModel>>> = flow {
        emit(Resource.Loading)
        searchService.searchPhone(query).suspendOnSuccess {
            val response = this.data
            if (response != null) {
                val phoneData = response.map { it.toShowPhoneModel() }
                emit(Resource.Success(phoneData))
            } else emit(Resource.Error(this.response.message()))

        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }
    }

    override suspend fun getPhoneItemDetails(phoneId: String): Flow<Resource<PhoneDetailShowModel>> = flow{
        emit(Resource.Loading)
        homeService.getPhoneDetails(phoneId).suspendOnSuccess {
            val response = this.data
            if (response.status){
                val phoneData = response.toPhoneDetailsDto()
                emit(Resource.Success(phoneData))
            } else {
                emit(Resource.Error("Unable Fetch Details"))
            }
        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }
    }

    override suspend fun insertPhoneEntity(showPhoneModel: ShowPhoneModel) {
        kotlin.runCatching {
            phoneDao.insertPhone(showPhoneModel.toPhoneEntity())
        }.onSuccess {

        }.onFailure {

        }
    }

    override suspend fun getAllPhones(): Flow<List<ShowPhoneModel>> {
        return phoneDao.getAllPhones().map {
            it.map { it.toPhoneEntity() }
        }
    }

    override suspend fun deletePhoneEntity(showPhoneModel: ShowPhoneModel) {
        kotlin.runCatching {
            phoneDao.deletePhone(showPhoneModel.toPhoneEntity())
        }.onSuccess {

        }.onFailure {

        }
    }
}