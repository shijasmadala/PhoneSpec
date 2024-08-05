package com.example.myownphone.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myownphone.home.data.entity.PhoneEntity
import com.example.myownphone.home.data.source.PhoneDao

@Database(entities = [PhoneEntity::class], version = 1)
abstract class PhoneDatabase : RoomDatabase() {
    abstract val phoneDao: PhoneDao
}