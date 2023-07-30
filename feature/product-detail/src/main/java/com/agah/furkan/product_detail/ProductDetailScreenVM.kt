package com.agah.furkan.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.data.model.Result
import com.agah.furkan.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailScreenVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) :
    ViewModel() {
    private val productId = savedStateHandle.get<Long>("productId") ?: 0

    private val _productDetail =
        MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val productDetail = _productDetail.asStateFlow()

    init {
        getProductDetail(productId)
    }

    fun getProductDetail(productId: Long) {
        viewModelScope.launch {
            val result = productRepository.getProductDetail(productId)

            val state = when (result) {
                is Result.Success -> {
                    ProductDetailUiState.Success(result.data.productDetail)
                }

                is Result.Failure -> {
                    ProductDetailUiState.Error(result.error.errorMessage)
                }
            }
            _productDetail.emit(state)
        }
    }
}
