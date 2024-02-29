package com.example.expapp.contacts.data.cache

import com.example.expapp.contacts.data.ContactData
import com.example.expapp.contacts.data.EducationPeriod
import com.example.expapp.contacts.data.Temperament
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactsCacheDataSourceTest {

    @Test
    fun test_all_contacts_empty() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(1))

        val actual = dataSource.allContacts()
        val expected = emptyList<ContactCache>()
        assertEquals(expected, actual)
    }

    @Test
    fun test_all_contacts_not_empty() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(2))

        dao.data.add(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                5
            )
        )

        dao.data.add(
            ContactCache(
                "2xyz234d",
                "Bob Bob",
                "+2 (234) 567-8910",
                190.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                6
            )
        )

        val actualList = dataSource.allContacts()
        val expectedList = listOf(
            ContactData(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
                EducationPeriod(
                    "2010-01-01T10:10:10-06:00",
                    "2012-02-02T12:12:12-06:00"
                )
            ),
            ContactData(
                "2xyz234d",
                "Bob Bob",
                "+2 (234) 567-8910",
                190.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
                EducationPeriod(
                    "2010-01-01T10:10:10-06:00",
                    "2012-02-02T12:12:12-06:00"
                )
            )
        )

        actualList.forEachIndexed { index, actual ->
            assertEquals(expectedList[index], actual)
        }
    }

    @Test
    fun test_contains() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(3))

        dao.data.add(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                4
            )
        )

        val actual = dataSource.contains("Mike Mike")
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun test_does_not_contain() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(5))

        dao.data.add(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                5
            )
        )

        val actual = dataSource.contains("Query")
        val expected = false
        assertEquals(expected, actual)
    }

    @Test
    fun test_save() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(6))

        dataSource.saveContact(
            ContactData(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
                EducationPeriod(
                    "2010-01-01T10:10:10-06:00",
                    "2012-02-02T12:12:12-06:00"
                )
            )
        )

        assertEquals(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                6
            ),
            dao.data[0]
        )
    }

    @Test
    fun test_contact_contains() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(7))

        dao.data.add(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                0
            )
        )

        val actual = dataSource.contact("Mike Mike")
        val expected = ContactData(
            "1abc234d",
            "Mike Mike",
            "+1 (234) 567-8910",
            200.1f,
            "Text bio",
            Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
            EducationPeriod("2010-01-01T10:10:10-06:00", "2012-02-02T12:12:12-06:00")
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_contact_does_not_exist() = runTest {
        val dao = TestDao()
        val dataSource = ContactsCacheDataSource.Base(dao, TestMapper(8))

        dao.data.add(
            ContactCache(
                "1abc234d",
                "Mike Mike",
                "+1 (234) 567-8910",
                200.1f,
                "Text bio",
                Temperament.TYPE_TEMPERAMENT_MELANCHOLIC.name,
                "2010-01-01T10:10:10-06:00",
                "2012-02-02T12:12:12-06:00",
                0
            )
        )

        val actual = dataSource.contact("Query")
        val expected = ContactData(
            "",
            "",
            "",
            0f,
            "",
            Temperament.TYPE_TEMPERAMENT_MELANCHOLIC,
            EducationPeriod("", "")
        )

        assertEquals(expected, actual)
    }
}

private class TestDao : ContactsDao {

    val data = mutableListOf<ContactCache>()

    override fun allContacts(): List<ContactCache> = data

    override fun insert(contact: ContactCache) {
        data.add(contact)
    }

    override fun searchContacts(searchQuery: String): ContactCache? =
        data.find { (it.name == searchQuery) || (it.phone == searchQuery) }
}

private class TestMapper(private val date: Long) : ContactData.Mapper<ContactCache> {

    override fun map(
        id: String,
        name: String,
        phone: String,
        height: Float,
        biography: String,
        temperament: Temperament,
        educationPeriod: EducationPeriod
    ): ContactCache = ContactCache(
        id,
        name,
        phone,
        height,
        biography,
        temperament.name,
        educationPeriod.start,
        educationPeriod.end,
        date
    )
}