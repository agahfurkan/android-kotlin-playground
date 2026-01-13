package com.agah.furkan.domain.cart

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.preferences.UserPreference
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(refresh: Boolean = false): DomainResult<List<Cart>> {
        val userId = userPreference.getUserId()
        return cartRepository.getCart(refresh = refresh, userId = userId)
    }
}

