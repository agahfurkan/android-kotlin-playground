package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.cart.CartRepository
import com.agah.furkan.data.cart.remote.model.response.CartResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference,
) : ViewModel() {

    private val _userCart = MutableStateFlow<Map<Long, List<CartResponse.Cart>>>(emptyMap())
    val userCart = _userCart.asStateFlow()

    private val _removeProductFromCart =
        MutableLiveData<Result<String>>()
    val removeProductFromCart: LiveData<Result<String>>
        get() = _removeProductFromCart

    private val _navigateToLoginScreen = Channel<Boolean>(capacity = Channel.BUFFERED)
    val navigateToLoginScreen = _navigateToLoginScreen.receiveAsFlow()

    fun refreshUserCart() {
        getUserCart(true)
    }

    private fun getUserCart(refresh: Boolean = false) {
        viewModelScope.launch {
            val result =
                cartRepository.getCart(refresh = refresh, userId = userPreference.getUserId())
            if (result is Result.Success) {
                val groupedResult = result.data.sortedBy { it.productId }
                    .groupBy { it.productId }
                _userCart.emit(groupedResult)
            }
        }
    }

    fun sessionEnded() {
        _navigateToLoginScreen.trySend(true)
    }
}
