package com.example.expapp.contacts.cache

import com.example.expapp.contacts.ContactData
import com.example.expapp.contacts.EducationPeriod
import com.example.expapp.contacts.Temperament
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface ContactsCacheDataSource : FetchContact {

    suspend fun allContacts(): List<ContactData>

    suspend fun contains(searchQuery: String): Boolean

    suspend fun saveContact(contactData: ContactData)

    class Base(
        private val dao: ContactsDao,
        private val dataToCache: ContactData.Mapper<ContactCache>
    ) : ContactsCacheDataSource {

        private val mutex = Mutex()

        override suspend fun allContacts(): List<ContactData> = mutex.withLock {
            val data = dao.allContacts()
            return data.map {
                ContactData(
                    it.id,
                    it.name,
                    it.phone,
                    it.height,
                    it.biography,
                    Temperament.valueOf(it.temperament),
                    EducationPeriod(
                        it.educationPeriodStart,
                        it.educationPeriodEnd
                    )
                )
            }
        }

        override suspend fun contains(searchQuery: String): Boolean = mutex.withLock {
            val data = dao.searchContacts(searchQuery)
            return data != null
        }

        override suspend fun saveContact(contactData: ContactData) = mutex.withLock {
            dao.insert(contactData.map(dataToCache))
        }

        override suspend fun contact(searchQuery: String): ContactData = mutex.withLock {
            val contactCache = dao.searchContacts(searchQuery) ?: ContactCache(
                "",
                "",
                "",
                0f,
                "",
                "",
                "",
                "",
                0
            )
            return ContactData(
                contactCache.id,
                contactCache.name,
                contactCache.phone,
                contactCache.height,
                contactCache.biography,
                Temperament.valueOf(contactCache.temperament),
                EducationPeriod(
                    contactCache.educationPeriodStart,
                    contactCache.educationPeriodEnd
                )
            )
        }
    }
}

interface FetchContact {
    suspend fun contact(searchQuery: String): ContactData
}