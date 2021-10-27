package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.domain.model.Cart
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.data.repository.CartRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.web.model.request.RemoveProductFromCartBody
import com.agah.furkan.androidplayground.data.web.model.response.AddProductToCartResponse
import com.agah.furkan.androidplayground.data.web.model.response.RemoveProductFromCartResponse
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val cartRepository: CartRepository) :
    ViewModel() {

    private val _addProductToCartResponse = MutableLiveData<ApiResponse<AddProductToCartResponse>>()
    val addProductToCartResponse: LiveData<ApiResponse<AddProductToCartResponse>>
        get() = _addProductToCartResponse

    private val _userCart = MutableLiveData<List<Cart>>()
    val userCart: LiveData<List<Cart>>
        get() = _userCart

    private val _removeProductFromCart =
        MutableLiveData<ApiResponse<RemoveProductFromCartResponse>>()
    val removeProductFromCart: LiveData<ApiResponse<RemoveProductFromCartResponse>>
        get() = _removeProductFromCart

    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            val response = cartRepository.addProductToCart(
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
            getUserCart()
            _addProductToCartResponse.postValue(response)
        }
    }

    fun getUserCart() {
        viewModelScope.launch {
            val response = cartRepository.getCart(userId = SharedPrefUtil.getUserId())
            _userCart.postValue(response)
        }
    }

    fun removeProductFromCart(productId: Long) {
        viewModelScope.launch {
            val response = cartRepository.removeProductFromCart(
                RemoveProductFromCartBody(
                    userId = SharedPrefUtil.getUserId(),
                    productId = productId
                )
            )
            getUserCart()
            _removeProductFromCart.postValue(response)
        }
    }
}
