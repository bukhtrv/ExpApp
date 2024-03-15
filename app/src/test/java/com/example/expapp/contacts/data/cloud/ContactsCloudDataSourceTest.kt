package com.example.expapp.contacts.data.cloud

import com.example.expapp.contacts.data.ContactData
import com.example.expapp.contacts.data.EducationPeriod
import com.example.expapp.contacts.data.Temperament
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactsCloudDataSourceTest {

    @Test
    fun `test get contact list success`() = runTest {
        val apiHeader = ContactApiHeader.Mock("test")
        val service = MockContactsService(apiHeader)
        val dataSource = ContactsCloudDataSource.Base(service)

        val actual = dataSource.getContactList("Mock.json")
        val expended = listOf(
            ContactData(
                "1",
                "First First",
                "+1 234 567 89 90",
                100.1f,
                "biography",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
                EducationPeriod("0", "1")
            ),
            ContactData(
                "2",
                "Second Second",
                "+1 234 567 89 90",
                202.2f,
                "biography",
                Temperament.TYPE_TEMPERAMENT_SANGUINE,
                EducationPeriod("0", "1")
            )
        )
        assertEquals(expended, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun `test get contact list failed`(): Unit = runTest {
        val apiHeader = ContactApiHeader.Mock("test")
        val service = MockContactsService(apiHeader)
        val dataSource = ContactsCloudDataSource.Base(service)

        dataSource.getContactList("")
    }
}