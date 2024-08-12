package com.example.myownphone.home

import com.example.myownphone.home.domain.model.ShowPhoneModel
import com.example.myownphone.home.data.dto.Phone
import com.example.myownphone.home.data.dto.search.SearchDetailDtoItem
import com.example.myownphone.home.data.entity.PhoneEntity

fun Phone.toShowPhoneModel(): ShowPhoneModel {
    return ShowPhoneModel(
        detail = detail, image = image, phoneName = phoneName, slug = slug, favorites = favorites, hits = hits
    )
}

fun SearchDetailDtoItem.toShowPhoneModel(): ShowPhoneModel {
    return ShowPhoneModel(
        detail = brand, image = image, phoneName = phoneName, slug = slug, favorites = 0, hits = 0
    )

}

fun ShowPhoneModel.toPhoneEntity(): PhoneEntity {
    return PhoneEntity(
        detail = detail,
        phoneName = phoneName,
        image = image,
        slug = slug?:"",
        isFavouriteAdded = isFavouriteAdded
    )
}

fun PhoneEntity.toPhoneEntity(): ShowPhoneModel {
    return ShowPhoneModel(
        detail = detail,
        phoneName = phoneName,
        image = image,
        slug = slug,
        isFavouriteAdded = isFavouriteAdded,
        favorites = 0,
        hits = 0
    )
}