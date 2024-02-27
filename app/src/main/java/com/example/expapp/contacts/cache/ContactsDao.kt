package com.example.expapp.contacts.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts_table")
    fun allContacts(): List<ContactCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: ContactCache)

    @Query(
        "SELECT *" +
                "FROM contacts_table " +
                "WHERE name LIKE '%'||:searchQuery||'%' OR phone LIKE '%'||:searchQuery||'%'"
    )
    fun searchContacts(searchQuery: String): ContactCache?
}