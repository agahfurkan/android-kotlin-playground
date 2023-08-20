package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.cart.CartRepository
import com.agah.furkan.cart.remote.model.response.CartResponse
import com.agah.furkan.preferences.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    private val _userCart = MutableStateFlow<Map<Long, List<CartResponse.Cart>>>(emptyMap())
    val userCart = _userCart.asStateFlow()

    private val _removeProductFromCart =
        MutableLiveData<com.agah.furkan.data.model.Result<String>>()
    val removeProductFromCart: LiveData<com.agah.furkan.data.model.Result<String>>
        get() = _removeProductFromCart

    init {
        getUserCart()
    }

    fun refreshUserCart() {
        getUserCart()
    }

    private fun getUserCart() {
        viewModelScope.launch {
            val result = cartRepository.fetchCart(userId = userPreference.getUserId())
            if (result is com.agah.furkan.data.model.Result.Success) {
                val groupedResult = result.data.sortedBy { it.productId }
                    .groupBy { it.productId }
                _userCart.emit(groupedResult)
            }
        }
    }
}
