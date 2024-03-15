package com.example.expapp.contacts.data.cloud

import com.example.expapp.contacts.data.ContactData

interface ContactsCloudDataSource {

    suspend fun getContactList(jsonFileName: String): List<ContactData>

    class Base(
        private val service: ContactsService,
    ) : ContactsCloudDataSource {

        override suspend fun getContactList(jsonFileName: String): List<ContactData> {

            val response = service.getContactList(jsonFileName)
            val contactCloudList =
                response.body() ?: throw IllegalStateException("service unavailable")
            val contactDataList = mutableListOf<ContactData>()

            if (contactCloudList.isNotEmpty()) {
                for (contact in contactCloudList) {
                    contactDataList.add(contact.map(ContactCloudToData()))
                }
            }

            return contactDataList
        }
    }
}