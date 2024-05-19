package com.armhansa.neversituptesting.data.mapper

import com.armhansa.neversituptesting.data.entity.DepartmentEntity
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import javax.inject.Inject

class DepartmentDisplayMapper @Inject constructor() {

    fun transformList(entityList: List<DepartmentEntity>): List<DepartmentDisplay> {
        return entityList.map(::transform)
    }

    private fun transform(entity: DepartmentEntity): DepartmentDisplay {
        entity.run {
            return DepartmentDisplay(
                id.orEmpty(),
                name.orEmpty(),
                imageUrl.orEmpty()
            )
        }
    }
}