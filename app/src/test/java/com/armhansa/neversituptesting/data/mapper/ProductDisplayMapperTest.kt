package com.armhansa.neversituptesting.data.mapper

import com.armhansa.neversituptesting.data.entity.ProductEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDisplayMapperTest {

    private val mapper = ProductDisplayMapper()

    @Test
    fun `test transformList should work properly`() {
        // GIVEN
        val entityList = listOf(
            ProductEntity(
                "Product 1",
                "imageUrl 1",
                "Desc 1",
                "100",
                "Food",
                "1",
                "1"
            ),
            ProductEntity(
                "Product 2",
                "imageUrl 2",
                "Desc 2",
                "200",
                "Food",
                "2",
                "1"
            ),
            ProductEntity(
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )

        // WHEN
        val result = mapper.transformList(entityList)

        // THEN
        assertEquals(3, result.size)
        result[0].run {
            assertEquals("Product 1", name)
            assertEquals("imageUrl 1", imageUrl)
            assertEquals("Desc 1", desc)
            assertEquals("100", price)
            assertEquals("Food", type)
            assertEquals("1", id)
            assertEquals("1", departmentId)
        }
        assertEquals(3, result.size)
        result[1].run {
            assertEquals("Product 2", name)
            assertEquals("imageUrl 2", imageUrl)
            assertEquals("Desc 2", desc)
            assertEquals("200", price)
            assertEquals("Food", type)
            assertEquals("2", id)
            assertEquals("1", departmentId)
        }
        assertEquals(3, result.size)
        result[2].run {
            assertEquals("", name)
            assertEquals("", imageUrl)
            assertEquals("", desc)
            assertEquals("", price)
            assertEquals("", type)
            assertEquals("", id)
            assertEquals("", departmentId)
        }
    }

}