package com.example.expapp.contacts.data

enum class Temperament {
    TYPE_TEMPERAMENT_MELANCHOLIC,
    TYPE_TEMPERAMENT_SANGUINE,
    TYPE_TEMPERAMENT_CHOLERIC,
    TYPE_TEMPERAMENT_PHLEGMATIC
}

data class EducationPeriod(
    val start: String,
    val end: String
)

data class ContactData(
    private val id: String,
    private val name: String,
    private val phone: String,
    private val height: Float,
    private val biography: String,
    private val temperament: Temperament,
    private val educationPeriod: EducationPeriod,
) {

    interface Mapper<T> {
        fun map(
            id: String,
            name: String,
            phone: String,
            height: Float,
            biography: String,
            temperament: Temperament,
            educationPeriod: EducationPeriod,
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(
        id,
        name,
        phone,
        height,
        biography,
        temperament,
        educationPeriod
    )
}