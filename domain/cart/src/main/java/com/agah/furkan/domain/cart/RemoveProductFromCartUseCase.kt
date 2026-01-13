package com.agah.furkan.domain.cart

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.preferences.UserPreference
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(productId: Long): DomainResult<String> {
        val userId = userPreference.getUserId()
        return cartRepository.removeProductFromCart(productId = productId, userId = userId)
    }
}

