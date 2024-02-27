package com.example.expapp.contacts.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class ContactCache(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "height") val height: Float,
    @ColumnInfo(name = "biography") val biography: String,
    @ColumnInfo(name = "temperament") val temperament: String,
    @ColumnInfo(name = "educationPeriodStart") val educationPeriodStart: String,
    @ColumnInfo(name = "educationPeriodEnd") val educationPeriodEnd: String,
    @ColumnInfo(name = "time") val time: Long
)