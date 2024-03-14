package com.example.expapp.contacts.data

import com.example.expapp.contacts.data.cache.ContactsCacheDataSource
import com.example.expapp.contacts.domain.ContactInfo
import com.example.expapp.contacts.domain.HandleError

interface HandleDataRequest {

    suspend fun handle(block: suspend () -> ContactData): ContactInfo

    class Base(
        private val cacheDataSource: ContactsCacheDataSource,
        private val mapperToDomain: ContactDataToDomain,
        private val handleError: HandleError<Exception>
    ) : HandleDataRequest {

        override suspend fun handle(block: suspend () -> ContactData) = try {
            val result = block.invoke()
            cacheDataSource.saveContact(result)
            result.map(mapperToDomain)
        } catch (e: Exception) {
            throw handleError.handle(e)
        }
    }
}