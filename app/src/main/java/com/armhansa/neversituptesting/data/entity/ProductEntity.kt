package com.armhansa.neversituptesting.data.entity

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName("name") val name: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("departmentId") val departmentId: String?,
)
