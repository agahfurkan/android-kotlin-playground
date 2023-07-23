package com.agah.furkan.androidplayground.data.mapper

import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.domain.model.result.ProductDetail
import com.agah.furkan.cart.Cart
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.product.remote.model.response.ProductDetailResponse
import com.agah.furkan.product.remote.model.response.ProductResponse

fun CartResponse.Cart.toDomainModel(): Cart {
    return Cart(
        cartId = cartId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}

fun ProductResponse.Product.toDomainModel(): Product {
    return Product(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}

fun ProductDetailResponse.ProductDetail.toDomainModel(): ProductDetail {
    return ProductDetail(
        categoryId = categoryId,
        discount = discount,
        picture = picture,
        price = price,
        productDescription = productDescription,
        productId = productId,
        productName = productName
    )
}

