package com.armhansa.neversituptesting.presentation.activity

import com.armhansa.neversituptesting.data.repository.ProductRepository
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: ProductRepository
) {

    private var view: MainView? = null

    private var departments: List<DepartmentDisplay>? = null

    fun setView(view: MainView) {
        this.view = view
    }

    fun start() {
        repository.getDepartmentsAndDefaultProducts(
            onSuccess = { departments, products ->
                this.departments = departments
                view?.run {
                    setupDepartmentsData(departments)
                    setupProductsData(products)
                }
            },
            onError = {
                // TODO: Show PopUp Error or Just Toast
            }
        )
    }

    fun onDepartmentChanged(index: Int) {
        repository.getProducts(
            onSuccess = {
                view?.setupProductsData(it)
            },
            onError = {
                // TODO: Show PopUp Error or Just Toast
            },
            departments?.get(index)?.id.orEmpty()
        )
    }

    fun onDestroy() {
        repository.destroy()
    }

}
