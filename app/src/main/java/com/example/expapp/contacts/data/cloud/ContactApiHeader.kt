package com.example.expapp.contacts.data.cloud

import okhttp3.Headers.Companion.toHeaders
import retrofit2.Response

interface ContactApiHeader {

    interface MockResponse {
        fun makeResponse(
            body: List<ContactCloud>,
            headerValue: String
        ): Response<List<ContactCloud>>
    }

    interface Combo : ContactApiHeader, MockResponse

    abstract class Abstract(private val header: String) : Combo {

        override fun makeResponse(
            body: List<ContactCloud>,
            headerValue: String
        ): Response<List<ContactCloud>> =
            Response.success(body, mapOf(header to headerValue).toHeaders())
    }

    class Base : Abstract("X-Contacts-API-Contact")
    class Mock(value: String = "X-Contacts-API-Mocked") : Abstract(value)
}