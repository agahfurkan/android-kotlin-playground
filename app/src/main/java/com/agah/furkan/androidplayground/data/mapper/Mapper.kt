package com.agah.furkan.androidplayground.data.mapper

import com.agah.furkan.androidplayground.data.remote.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.remote.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.data.remote.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.data.remote.model.response.CategoryResponse
import com.agah.furkan.androidplayground.data.remote.model.response.ProductDetailResponse
import com.agah.furkan.androidplayground.data.remote.model.response.ProductResponse
import com.agah.furkan.androidplayground.data.remote.model.response.UserLoginResponse
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.model.result.LoginResult
import com.agah.furkan.androidplayground.domain.model.result.Product
import com.agah.furkan.androidplayground.domain.model.result.ProductDetail
import com.agah.furkan.cart.remote.model.response.CartResponse

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

fun CategoryResponse.Category.toDomainModel(): Category {
    return Category(categoryId = categoryId, categoryName = categoryName)
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

fun UserLoginResponse.toDomainModel(): LoginResult {
    if (token == null || userId == null) {
        throw IllegalStateException("cannot be null")
    }
    return LoginResult(token = token, userId = userId, message = message ?: "")
}

fun UseCaseParams.UserLoginParams.toRequestModel(): UserLoginBody {
    return UserLoginBody(password = password, username = username)
}

fun UseCaseParams.UserRegisterParams.toRequestModel(): UserRegisterBody {
    return UserRegisterBody(password = password, username = username)
}

fun UseCaseParams.ValidateTokenParams.toRequestModel(): ValidateTokenBody {
    return ValidateTokenBody(token = token)
}
