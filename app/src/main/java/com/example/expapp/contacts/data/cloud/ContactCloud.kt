package com.example.expapp.contacts.data.cloud

import com.example.expapp.contacts.data.Temperament
import com.google.gson.annotations.SerializedName

data class EducationPeriodCloud(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)

enum class TemperamentCloud(
    val cloudName: String,
    val temperamentCache: Temperament
) {
    @SerializedName("melancholic")
    TYPE_CLOUD_TEMPERAMENT_MELANCHOLIC(
        "melancholic",
        Temperament.TYPE_TEMPERAMENT_MELANCHOLIC
    ),

    @SerializedName("sanguine")
    TYPE_CLOUD_TEMPERAMENT_SANGUINE(
        "sanguine",
        Temperament.TYPE_TEMPERAMENT_SANGUINE
    ),

    @SerializedName("choleric")
    TYPE_CLOUD_TEMPERAMENT_CHOLERIC(
        "choleric",
        Temperament.TYPE_TEMPERAMENT_CHOLERIC
    ),

    @SerializedName("phlegmatic")
    TYPE_CLOUD_TEMPERAMENT_PHLEGMATIC(
        "phlegmatic",
        Temperament.TYPE_TEMPERAMENT_PHLEGMATIC
    )
}

data class ContactCloud(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("height")
    val height: Float,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("temperament")
    val temperament: TemperamentCloud,
    @SerializedName("educationPeriod")
    val educationPeriodCloud: EducationPeriodCloud
) {

    interface Mapper<T> {
        fun map(
            id: String,
            name: String,
            phone: String,
            height: Float,
            biography: String,
            temperament: TemperamentCloud,
            educationPeriod: EducationPeriodCloud,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(
        id,
        name,
        phone,
        height,
        biography,
        temperament,
        educationPeriodCloud
    )
}