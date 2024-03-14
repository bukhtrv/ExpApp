package com.example.expapp.contacts.domain

abstract class DomainException : IllegalStateException()

class NoInternetConnectionException : DomainException()

class ServiceUnavailableException : DomainException()