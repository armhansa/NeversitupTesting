package com.armhansa.neversituptesting.presentation.activity

import com.armhansa.neversituptesting.data.repository.ProductRepository
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repository: ProductRepository
) {

    private var view: MainView? = null
    private var currentDepartmentId: String = ""
    private var departments: List<DepartmentDisplay>? = null

    fun setView(view: MainView) {
        this.view = view
    }

    fun start() {
        view?.showFullscreenLoading()
        repository.getDepartmentsAndDefaultProducts(
            onSuccess = { departments, products ->
                view?.run {
                    this@MainPresenter.departments = departments
                    currentDepartmentId = departments.first().id
                    setupDepartmentsData(departments)
                    setupProductsData(products, departments.first())
                    hideFullscreenLoading()
                }
            },
            onError = {
                view?.hideFullscreenLoading()
                // TODO: Show PopUp Error or Just Toast
            }
        )
    }

    fun onDepartmentChanged(department: DepartmentDisplay) {
        if (department.id != currentDepartmentId) {
            view?.showFullscreenLoading()
            repository.getProducts(
                onSuccess = {
                    view?.run {
                        currentDepartmentId = department.id
                        setupProductsData(it, department)
                        hideFullscreenLoading()
                    }
                },
                onError = {
                    view?.hideFullscreenLoading()
                    // TODO: Show PopUp Error or Just Toast
                },
                department.id
            )
        }
    }

    fun onDestroy() {
        repository.destroy()
    }

}
