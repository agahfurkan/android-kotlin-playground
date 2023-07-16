package com.agah.furkan.product.remote.model.response

import com.agah.furkan.data.model.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailResponse(
    @Json(name = "productDetail")
    val productDetail: ProductDetail
) : BaseResponse() {
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
