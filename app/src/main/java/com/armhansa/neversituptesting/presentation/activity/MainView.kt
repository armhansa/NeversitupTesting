package com.armhansa.neversituptesting.presentation.activity

import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.armhansa.neversituptesting.presentation.display.ProductDisplay

interface MainView {
    fun showFullscreenLoading()
    fun hideFullscreenLoading()
    fun setupDepartmentsData(departments: List<DepartmentDisplay>)
    fun setupProductsData(products: List<ProductDisplay>, department: DepartmentDisplay)
}
