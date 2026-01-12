package com.agah.furkan.domain.cart

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.cart.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(refresh: Boolean = false): Result<List<Cart>> {
        val userId = userPreference.getUserId()
        return when (val result = cartRepository.getCart(refresh = refresh, userId = userId)) {
            is Result.Success -> Result.Success(result.data.map { it.toDomain() })
            is Result.Failure -> Result.Failure(result.error)
        }
    }

    private fun com.agah.furkan.data.cart.model.CartDomainModel.toDomain(): Cart {
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
}

