package com.agah.furkan.feature.product_detail_tabbed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.data.product.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductTabbedDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productDetailRepository: ProductDetailRepository
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