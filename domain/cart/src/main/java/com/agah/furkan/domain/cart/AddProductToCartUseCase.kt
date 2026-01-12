package com.agah.furkan.domain.cart

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.cart.CartRepository
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(productId: Long): Result<String> {
        val userId = userPreference.getUserId()
        return cartRepository.addProductToCart(productId = productId, userId = userId)
    }
}

