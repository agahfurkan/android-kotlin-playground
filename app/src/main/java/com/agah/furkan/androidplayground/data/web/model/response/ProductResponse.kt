package com.agah.furkan.androidplayground.data.web.model.response


import com.squareup.moshi.Json

data class ProductResponse(
    @Json(name = "categoryId")
    val categoryId: Int,
    @Json(name = "discount")
    val discount: Double,
    @Json(name = "picture")
    val picture: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "productDescription")
    val productDescription: String,
    @Json(name = "productName")
    val productName: String
)