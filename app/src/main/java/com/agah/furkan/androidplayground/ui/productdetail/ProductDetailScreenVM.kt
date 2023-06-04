package com.agah.furkan.androidplayground.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.ProductRepositoryImpl
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.ProductDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailScreenVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepositoryImpl
) :
    ViewModel() {
    private val productId = savedStateHandle.get<Long>("productId") ?: 0

    private val _productDetail = MutableLiveData<Result<ProductDetail>>()
    val productDetail: LiveData<Result<ProductDetail>> get() = _productDetail

    init {
        getProductDetail(productId)
    }

    fun getProductDetail(productId: Long) {
        viewModelScope.launch {
            val result = productRepository.getProductDetail(productId)
            _productDetail.postValue(result)
        }
    }
}
