package com.agah.furkan.androidplayground.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.CartRepository
import com.agah.furkan.androidplayground.data.repository.CategoryRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.CartBody
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragmentVM @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val cartRepository: CartRepository
) :
    ViewModel() {
    private val _categoryList = MutableLiveData<ApiResponse<List<CategoryResponse>>>()
    val categoryList: LiveData<ApiResponse<List<CategoryResponse>>> get() = _categoryList

    private val _cartResponse = MutableLiveData<ApiResponse<List<CartResponse>>>()
    val cartResponse: LiveData<ApiResponse<List<CartResponse>>> get() = _cartResponse

    init {
        fetchMainProductCategories()
        fetchCart()
    }

    fun fetchMainProductCategories() {
        viewModelScope.launch {
            val response = categoryRepository.fetchMainProductCategories()
            _categoryList.postValue(response)
        }
    }

    fun fetchCart() {
        viewModelScope.launch {
            val response = cartRepository.getCart(CartBody(username = SharedPrefUtil.getUsername()))
        }
    }
}
