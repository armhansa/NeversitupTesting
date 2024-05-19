package com.armhansa.neversituptesting.data.repository

import com.armhansa.neversituptesting.data.mapper.DepartmentDisplayMapper
import com.armhansa.neversituptesting.data.mapper.ProductDisplayMapper
import com.armhansa.neversituptesting.data.service.ProductService
import com.armhansa.neversituptesting.presentation.display.DepartmentDisplay
import com.armhansa.neversituptesting.presentation.display.MainPageDisplay
import com.armhansa.neversituptesting.presentation.display.ProductDisplay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: ProductService,
    private val departmentMapper: DepartmentDisplayMapper,
    private val productMapper: ProductDisplayMapper,
) {

    private val disposable = CompositeDisposable()

    fun getDepartmentsAndDefaultProducts(
        onSuccess: (departments: List<DepartmentDisplay>, products: List<ProductDisplay>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        Single.zip(
            productService.getDepartments(),
            productService.getProducts()
        ) { departments, products ->
            MainPageDisplay(
                departmentMapper.transformList(departments),
                productMapper.transformList(products)
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it.departments, it.products) },
                { onError(it) }
            )
            .addToRepo()
    }

    fun getProducts(
        onSuccess: (List<ProductDisplay>) -> Unit,
        onError: (Throwable) -> Unit,
        departmentId: String = DEFAULT_DEPARTMENT_ID,
    ) {
        productService.getProducts(departmentId)
            .subscribeOn(Schedulers.io())
            .map(productMapper::transformList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            )
            .addToRepo()
    }

    private fun Disposable.addToRepo() {
        disposable.add(this)
    }

    fun destroy() {
        disposable.dispose()
    }

    companion object {
        const val DEFAULT_DEPARTMENT_ID = "1"
    }
}