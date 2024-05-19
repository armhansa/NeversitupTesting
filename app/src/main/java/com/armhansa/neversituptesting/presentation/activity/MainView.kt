package com.armhansa.neversituptesting.presentation.activity

import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.armhansa.neversituptesting.presentation.display.ProductDisplay

interface MainView {
    fun setupDepartmentsData(display: List<DepartmentDisplay>)
    fun setupProductsData(display: List<ProductDisplay>)
}
