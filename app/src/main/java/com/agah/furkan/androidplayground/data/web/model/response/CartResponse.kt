package com.agah.furkan.androidplayground.data.web.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "cartList")
    val cartList: List<Cart>?
) : GenericResponse() {

    @JsonClass(generateAdapter = true)
    data class Cart(
        @Json(name = "cartId")
        val cartId: Int,
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