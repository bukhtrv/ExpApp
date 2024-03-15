package com.example.expapp.contacts.data.cloud

import retrofit2.Response

class MockContactsService(
    private val contactApiHeader: ContactApiHeader.MockResponse
) : ContactsService {

    private var idContactMock = 0

    override suspend fun getContactList(jsonFileName: String): Response<List<ContactCloud>> {

        if (jsonFileName.isEmpty()) throw IllegalStateException("service unavailable")

        val contactCloudList = listOf(
            ContactCloud(
                (++idContactMock).toString(),
                "First First",
                "+1 234 567 89 90",
                100.1f,
                "biography",
                TemperamentCloud.TYPE_CLOUD_TEMPERAMENT_MELANCHOLIC,
                EducationPeriodCloud("0", "1")
            ),
            ContactCloud(
                (++idContactMock).toString(),
                "Second Second",
                "+1 234 567 89 90",
                202.2f,
                "biography",
                TemperamentCloud.TYPE_CLOUD_TEMPERAMENT_SANGUINE,
                EducationPeriodCloud("0", "1")
            )
        )

        return contactApiHeader.makeResponse(contactCloudList, "mock")
    }
}