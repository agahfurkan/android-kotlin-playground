package com.agah.furkan.androidplayground.data.web.model.response

import com.agah.furkan.androidplayground.data.domain.DomainModelConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.agah.furkan.androidplayground.data.domain.model.Product as ProductDomainModel

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "productList")
    val productList: List<Product>

) : GenericResponse() {
    @JsonClass(generateAdapter = true)
    data class Product(
        @Json(name = "categoryId")
        val categoryId: Long,
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
    ) : DomainModelConverter<ProductDomainModel> {
        override fun toDomainModel(): ProductDomainModel {
            return ProductDomainModel(
                categoryId = categoryId,
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
