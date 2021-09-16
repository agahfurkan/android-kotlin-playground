package com.agah.furkan.androidplayground.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.ProductRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailFragmentVM @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private val _productDetail = MutableLiveData<ApiResponse<ProductDetailResponse>>()
    val productDetail: LiveData<ApiResponse<ProductDetailResponse>> get() = _productDetail

    fun getProductDetail(productId: Int) {
        viewModelScope.launch {
            val response = productRepository.getProductDetail(productId)
            _productDetail.postValue(response)
        }
    }
}
