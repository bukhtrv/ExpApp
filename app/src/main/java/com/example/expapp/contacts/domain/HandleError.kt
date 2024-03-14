package com.example.expapp.contacts.domain

import com.example.expapp.R
import com.example.expapp.contacts.presentation.ManageResources

interface HandleError<T> {

    fun handle(e: Exception): T

    class Base(private val manageResources: ManageResources) : HandleError<String> {
        override fun handle(e: Exception) = manageResources.string(
            when (e) {
                is NoInternetConnectionException -> R.string.no_connection_message
                else -> R.string.service_is_unavailable
            }
        )
    }
}