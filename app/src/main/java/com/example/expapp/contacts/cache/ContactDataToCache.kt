package com.example.expapp.contacts.cache

import com.example.expapp.contacts.ContactData
import com.example.expapp.contacts.EducationPeriod
import com.example.expapp.contacts.Temperament

class ContactDataToCache : ContactData.Mapper<ContactCache> {
    override fun map(
        id: String,
        name: String,
        phone: String,
        height: Float,
        biography: String,
        temperament: Temperament,
        educationPeriod: EducationPeriod,
    ) =
        ContactCache(
            id,
            name,
            phone,
            height,
            biography,
            temperament.name,
            educationPeriod.start,
            educationPeriod.end,
            System.currentTimeMillis()
        )
}