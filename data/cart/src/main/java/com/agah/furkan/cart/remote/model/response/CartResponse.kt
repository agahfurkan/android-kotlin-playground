package com.agah.furkan.cart.remote.model.response

import com.agah.furkan.core.data.model.BaseResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "cartList")
    val cartList: List<Cart>
) : BaseResponse() {

    @JsonClass(generateAdapter = true)
    data class Cart(
        @Json(name = "cartId")
        val cartId: Long,
        @Json(name = "discount")
        val discount: Double,
        @Json(name = "picture")
        val picture: String,
        @Json(name = "price")
        val price: Double,
        @Json(name = "productDescription")
        val productDescription: String,
        @Json(name = "productId")
        val productId: Long,
        @Json(name = "productName")
        val productName: String
    )
}
