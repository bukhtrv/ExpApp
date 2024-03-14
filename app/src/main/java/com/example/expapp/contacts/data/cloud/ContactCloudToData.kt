package com.example.expapp.contacts.data.cloud

import com.example.expapp.contacts.data.ContactData
import com.example.expapp.contacts.data.EducationPeriod

class ContactCloudToData : ContactCloud.Mapper<ContactData> {

    override fun map(
        id: String,
        name: String,
        phone: String,
        height: Float,
        biography: String,
        temperament: TemperamentCloud,
        educationPeriod: EducationPeriodCloud,
    ) =
        ContactData(
            id,
            name,
            phone,
            height,
            biography,
            temperament.temperamentCache,
            EducationPeriod(
                educationPeriod.start,
                educationPeriod.end
            )
        )
}