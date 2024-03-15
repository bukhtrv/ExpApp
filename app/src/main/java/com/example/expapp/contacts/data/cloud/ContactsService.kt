package com.example.expapp.contacts.data.cloud

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsService {

    @GET("{jsonFileName}")
    suspend fun getContactList(@Path("jsonFileName") jsonFileName: String): Response<List<ContactCloud>>
}