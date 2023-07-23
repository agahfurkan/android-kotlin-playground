package com.agah.furkan.product_detail_tabbed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductTabbedDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productDetailRepository: com.agah.furkan.product.ProductDetailRepository
) : ViewModel() {
    private val productId = savedStateHandle.get<Long>("productId") ?: 0
    private val _productDetail = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val productDetail = _productDetail.asStateFlow()

    init {
        fetchProductDetails()
    }

    private fun fetchProductDetails() {
        viewModelScope.launch {
            productDetailRepository.getProductDetails().collect {
                _productDetail.emit(ProductDetailState.Success(it))
            }
        }
    }
}