package com.example.myownphone.di


import com.example.myownphone.home.data.source.HomeService
import com.example.myownphone.home.data.source.SearchService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    @Named("Normal")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://phone-specs-api.vercel.app/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("Search")
    fun provideSearchApiRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder().baseUrl("https://phonewise.onrender.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHomeService(@Named("Normal") retrofit: Retrofit) : HomeService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideSearchService(@Named("Search") retrofit: Retrofit) : SearchService {
        return retrofit.create()
    }
}