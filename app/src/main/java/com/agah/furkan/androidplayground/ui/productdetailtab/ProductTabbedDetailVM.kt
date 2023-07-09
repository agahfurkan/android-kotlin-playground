package com.agah.furkan.androidplayground.ui.productdetailtab

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.base.BaseViewModel
import com.agah.furkan.androidplayground.domain.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductTabbedDetailVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productDetailRepository: ProductDetailRepository
) :
    BaseViewModel() {
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