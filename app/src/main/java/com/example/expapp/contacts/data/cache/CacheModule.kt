package com.example.expapp.contacts.data.cache

import android.content.Context
import androidx.room.Room

interface CacheModule {
    fun provideDatabase(): ContactsDataBase

    class Base(private val context: Context) : CacheModule {

        private val dataBase by lazy {
            return@lazy Room.databaseBuilder(
                context,
                ContactsDataBase::class.java,
                "contacts_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        override fun provideDatabase(): ContactsDataBase = dataBase
    }

    class Mock(private val context: Context) : CacheModule {

        private val dataBase by lazy {
            Room.inMemoryDatabaseBuilder(context, ContactsDataBase::class.java)
                .build()
        }

        override fun provideDatabase(): ContactsDataBase = dataBase
    }
}