package com.example.myownphone.di

import android.app.Application
import androidx.room.Room
import com.example.myownphone.di.database.PhoneDatabase
import com.example.myownphone.home.data.source.PhoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePhoneDataBase(application: Application) =
        Room.databaseBuilder(application, PhoneDatabase::class.java, "phone_database")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providePhoneDao(phoneDatabase: PhoneDatabase) : PhoneDao{
        return phoneDatabase.phoneDao
    }
}