package com.armhansa.neversituptesting.data.service

import com.armhansa.neversituptesting.data.entity.DepartmentEntity
import com.armhansa.neversituptesting.data.entity.ProductEntity
import com.armhansa.neversituptesting.data.repository.ProductRepository.Companion.DEFAULT_DEPARTMENT_ID
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("api/v1/departments")
    fun getDepartments(): Single<List<DepartmentEntity>>

    @GET("api/v1/departments/{departmentId}/products")
    fun getProducts(
        @Path("departmentId") departmentId: String = DEFAULT_DEPARTMENT_ID
    ): Single<List<ProductEntity>>

}
