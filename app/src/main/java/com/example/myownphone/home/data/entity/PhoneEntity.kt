package com.example.myownphone.home.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhoneEntity(
    @PrimaryKey
    val slug: String,
    val detail: String?,
    val image: String?,
    val phoneName: String?,
    val isFavouriteAdded: Boolean
)