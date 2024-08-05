package com.example.myownphone.home.data.source

import com.example.myownphone.home.data.dto.ShowPhoneResponseDto
import com.example.myownphone.home.data.dto.search.SearchDetailDtoItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search?")
    suspend fun searchPhone(@Query("q") query: String): ApiResponse<List<SearchDetailDtoItem>>
}