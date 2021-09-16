package com.agah.furkan.androidplayground.ui.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.ProductRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListFragmentVM @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _productList = MutableLiveData<ApiResponse<ProductResponse>>()
    val productList: LiveData<ApiResponse<ProductResponse>> get() = _productList

    fun getProducts(categoryId: Int) {
        viewModelScope.launch {
            val response = productRepository.getProductList(categoryId = categoryId)
            _productList.postValue(response)
        }
    }
}
