package com.armhansa.neversituptesting.data.entity

import com.google.gson.annotations.SerializedName

data class DepartmentEntity(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
)
