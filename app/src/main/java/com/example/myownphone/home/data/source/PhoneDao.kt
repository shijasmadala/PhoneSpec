package com.example.myownphone.home.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myownphone.home.data.entity.PhoneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhone(phoneEntity: PhoneEntity)

    @Query("SELECT * from phoneentity")
    fun getAllPhones() : Flow<List<PhoneEntity>>

    @Delete
    fun deletePhone(phoneEntity: PhoneEntity)
}