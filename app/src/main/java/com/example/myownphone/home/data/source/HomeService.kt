package com.example.myownphone.home.data.source

import com.example.myownphone.detail.data.dto.PhoneDetailsShowDto
import com.example.myownphone.home.data.dto.ShowPhoneResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("{category}")
    suspend fun getLatestPhones(@Path("category") category: String) : ApiResponse<ShowPhoneResponseDto>

    @GET("{phone_id}")
    suspend fun getPhoneDetails(@Path("phone_id") phoneId : String) : ApiResponse<PhoneDetailsShowDto>
}