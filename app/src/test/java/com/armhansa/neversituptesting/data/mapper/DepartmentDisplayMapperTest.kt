package com.armhansa.neversituptesting.data.mapper

import com.armhansa.neversituptesting.data.entity.DepartmentEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class DepartmentDisplayMapperTest {

    private val mapper = DepartmentDisplayMapper()

    @Test
    fun `test transformList should work properly`() {
        // GIVEN
        val entityList = listOf(
            DepartmentEntity("1", "Movies", "imageUrl"),
            DepartmentEntity("2", "Health", "imageUrl"),
            DepartmentEntity(null, null, null)
        )

        // WHEN
        val result = mapper.transformList(entityList)

        // THEN
        assertEquals(3, result.size)
        result[0].run {
            assertEquals("1", id)
            assertEquals("Movies", name)
            assertEquals("imageUrl", imageUrl)
        }
        assertEquals(3, result.size)
        result[1].run {
            assertEquals("2", id)
            assertEquals("Health", name)
            assertEquals("imageUrl", imageUrl)
        }
        assertEquals(3, result.size)
        result[2].run {
            assertEquals("", id)
            assertEquals("", name)
            assertEquals("", imageUrl)
        }
    }

}