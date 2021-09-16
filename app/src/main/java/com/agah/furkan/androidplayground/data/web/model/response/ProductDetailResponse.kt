package com.agah.furkan.androidplayground.data.web.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailResponse(
    @Json(name = "productDetail")
    val productDetail: ProductDetail?
) : GenericResponse() {
    @JsonClass(generateAdapter = true)
    data class ProductDetail(
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
        @Json(name = "productId")
        val productId: Int,
        @Json(name = "productName")
        val productName: String
    )
}
