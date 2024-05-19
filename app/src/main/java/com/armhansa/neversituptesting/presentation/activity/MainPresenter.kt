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
        view?.showFullscreenLoading()
        repository.getDepartmentsAndDefaultProducts(
            onSuccess = { departments, products ->
                this.departments = departments
                view?.run {
                    setupDepartmentsData(departments)
                    setupProductsData(products)
                    hideFullscreenLoading()
                }
            },
            onError = {
                view?.hideFullscreenLoading()
                // TODO: Show PopUp Error or Just Toast
            }
        )
    }

    fun onDepartmentChanged(index: Int) {
        view?.showFullscreenLoading()
        repository.getProducts(
            onSuccess = {
                view?.run {
                    setupProductsData(it)
                    hideFullscreenLoading()
                }
            },
            onError = {
                view?.hideFullscreenLoading()
                // TODO: Show PopUp Error or Just Toast
            },
            departments?.get(index)?.id.orEmpty()
        )
    }

    fun onDestroy() {
        repository.destroy()
    }

}
