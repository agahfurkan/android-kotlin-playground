package com.agah.furkan.androidplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.CartRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.AddProductToCartBody
import com.agah.furkan.androidplayground.data.web.model.response.AddProductToCartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
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

    private val _userCartResponse = MutableLiveData<ApiResponse<CartResponse>>()
    val userCartResponse: LiveData<ApiResponse<CartResponse>>
        get() = _userCartResponse

    fun addProductToCart(addProductToCartBody: AddProductToCartBody) {
        viewModelScope.launch {
            val response = cartRepository.addProductToCart(addProductToCartBody)
            _addProductToCartResponse.postValue(response)
        }
    }

    fun getUserCart() {
        viewModelScope.launch {
            cartRepository.getCart(userId = SharedPrefUtil.getUserId())
        }
    }
}