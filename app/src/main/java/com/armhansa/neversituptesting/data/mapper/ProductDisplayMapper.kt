package com.armhansa.neversituptesting.data.mapper

import com.armhansa.neversituptesting.data.entity.ProductEntity
import com.armhansa.neversituptesting.presentation.display.ProductDisplay
import javax.inject.Inject

class ProductDisplayMapper @Inject constructor() {

    fun transformList(entityList: List<ProductEntity>): List<ProductDisplay> {
        return entityList.map(::transform)
    }

    private fun transform(entity: ProductEntity): ProductDisplay {
        entity.run {
            return ProductDisplay(
                name = name.orEmpty(),
                imageUrl = imageUrl.orEmpty(),
                desc = desc.orEmpty(),
                price = price.orEmpty(),
                type = type.orEmpty(),
                id = id.orEmpty(),
                departmentId = departmentId.orEmpty(),
            )
        }
    }
}
