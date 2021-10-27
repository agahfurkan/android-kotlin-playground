package com.agah.furkan.androidplayground.data.web.model.response

import com.agah.furkan.androidplayground.data.domain.DomainModelConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.agah.furkan.androidplayground.data.domain.model.Cart as CartDomainModel

@JsonClass(generateAdapter = true)
data class CartResponse(
    @Json(name = "cartList")
    val cartList: List<Cart>?
) : GenericResponse() {

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
    ) : DomainModelConverter<CartDomainModel> {
        override fun toDomainModel(): CartDomainModel {
            return CartDomainModel(
                cartId = cartId,
                discount = discount,
                picture = picture,
                price = price,
                productDescription = productDescription,
                productId = productId,
                productName = productName
            )
        }
    }
}
