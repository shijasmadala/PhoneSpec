package com.example.myownphone.detail.data.dto


import android.os.Parcelable
import com.example.myownphone.detail.domain.model.dto.PhoneDetailShowModel
import com.example.myownphone.detail.domain.model.dto.ShowSpec
import com.example.myownphone.detail.domain.model.dto.ShowSpecification
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PhoneDetailsShowDto(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "status")
    val status: Boolean
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Data(
        @Json(name = "brand")
        val brand: String,
        @Json(name = "dimension")
        val dimension: String,
        @Json(name = "os")
        val os: String,
        @Json(name = "phone_images")
        val phoneImages: List<String>,
        @Json(name = "phone_name")
        val phoneName: String,
        @Json(name = "release_date")
        val releaseDate: String,
        @Json(name = "specifications")
        val specifications: List<Specification>,
        @Json(name = "storage")
        val storage: String,
        @Json(name = "thumbnail")
        val thumbnail: String
    ) : Parcelable {
        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Specification(
            @Json(name = "specs")
            val specs: List<Spec>,
            @Json(name = "title")
            val title: String
        ) : Parcelable {


            fun toShowSpecification(): ShowSpecification {
                return ShowSpecification(
                    title = title, showSpecs = specs.map { it.toShowSpec() }
                )
            }

            @JsonClass(generateAdapter = true)
            @Parcelize
            data class Spec(
                @Json(name = "key")
                val key: String,
                @Json(name = "val")
                val valX: List<String>
            ) : Parcelable {


                fun toShowSpec(): ShowSpec {
                    return ShowSpec(
                        keySpec = key,
                        keyDetail = valX
                    )
                }

            }
        }
    }

    fun toPhoneDetailsDto(): PhoneDetailShowModel {
        return PhoneDetailShowModel(
            brand = data.brand,
            phoneName = data.phoneName,
            thumbnail = data.thumbnail,
            releaseDate = data.releaseDate,
            os = data.os,
            dimension = data.dimension,
            storage = data.storage,
            imageLists = data.phoneImages,
            specification = data.specifications.map { it.toShowSpecification() })
    }
}