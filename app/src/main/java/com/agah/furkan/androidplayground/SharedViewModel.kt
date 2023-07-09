package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.base.BaseViewModel
import com.agah.furkan.androidplayground.data.remote.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.domain.repository.CartRepository
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val cartRepository: CartRepository) :
    BaseViewModel() {

    private val _userCart = MutableStateFlow<Map<Long, List<Cart>>>(emptyMap())
    val userCart = _userCart.asStateFlow()

    private val _removeProductFromCart =
        MutableLiveData<Result<String>>()
    val removeProductFromCart: LiveData<Result<String>>
        get() = _removeProductFromCart

    init {
        getUserCart()
    }

    fun addProductToCart(productId: Int) {
        viewModelScope.launch {
            val result = cartRepository.addProductToCart(
                AddProductToCartBody(
                    productId = productId.toLong(),
                    userId = SharedPrefUtil.getUserId()
                )
            )
            if (result is Result.Success) {
                getUserCart()
            }
        }
    }

    private fun getUserCart() {
        viewModelScope.launch {
            val result = cartRepository.fetchCart(userId = SharedPrefUtil.getUserId())
            if (result is Result.Success) {
                val groupedResult = result.data.sortedBy { it.productId }.groupBy { it.productId }
                _userCart.emit(groupedResult)
            }
        }
    }

    fun removeProductFromCart(productId: Long) {
        viewModelScope.launch {
            val result = cartRepository.removeProductFromCart(
                RemoveProductFromCartBody(
                    userId = SharedPrefUtil.getUserId(),
                    productId = productId
                )
            )
            getUserCart()
            _removeProductFromCart.postValue(result)
        }
    }
}
