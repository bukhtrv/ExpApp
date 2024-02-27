package com.example.expapp.contacts.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactCache::class], version = 1)
abstract class ContactsDataBase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao
}