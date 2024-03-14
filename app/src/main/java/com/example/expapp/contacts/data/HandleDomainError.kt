package com.example.expapp.contacts.data

import com.example.expapp.contacts.domain.HandleError
import com.example.expapp.contacts.domain.NoInternetConnectionException
import com.example.expapp.contacts.domain.ServiceUnavailableException
import java.net.UnknownHostException

class HandleDomainError : HandleError<Exception> {

    override fun handle(e: Exception) = when (e) {
        is UnknownHostException -> NoInternetConnectionException()
        else -> ServiceUnavailableException()
    }
}