package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.base.BaseViewModel
import com.agah.furkan.androidplayground.data.remote.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.remote.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.domain.model.result.Product
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

    private val _userCart = MutableStateFlow<List<Cart>>(listOf())
    val userCart = _userCart.asStateFlow()

    private val _removeProductFromCart =
        MutableLiveData<Result<String>>()
    val removeProductFromCart: LiveData<Result<String>>
        get() = _removeProductFromCart

    init {
        getUserCart()
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            val result = cartRepository.addProductToCart(
                AddProductToCartBody(
                    productId = product.productId,
                    userId = SharedPrefUtil.getUserId(),
                    discount = product.discount,
                    picture = product.picture,
                    price = product.price,
                    productDescription = product.productDescription,
                    productName = product.productName
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
                _userCart.emit(result.data)
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
