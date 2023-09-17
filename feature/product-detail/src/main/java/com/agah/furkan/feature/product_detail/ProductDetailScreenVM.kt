package com.agah.furkan.feature.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.cart.CartRepository
import com.agah.furkan.data.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductDetailScreenVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val userPreference: UserPreference
) :
    ViewModel() {
    private val productId = savedStateHandle.get<Long>("productId") ?: 0

    private val _productDetail =
        MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val productDetail = _productDetail.asStateFlow()

    private val _addProductToCartState =
        Channel<AddProductToCartUiState>(capacity = Channel.BUFFERED)
    val addProductToCartState = _addProductToCartState.receiveAsFlow()

    init {
        getProductDetail(productId)
    }

    fun getProductDetail(productId: Long) {
        viewModelScope.launch {
            val result = productRepository.getProductDetail(productId)

            val state = when (result) {
                is Result.Success -> {
                    ProductDetailUiState.Success(result.data.asUiModel())
                }

                is Result.Failure -> {
                    ProductDetailUiState.Error(result.error.errorMessage)
                }
            }
            _productDetail.emit(state)
        }
    }

    fun addProductToCart(productId: Int) {
        viewModelScope.launch {
            _addProductToCartState.trySend(AddProductToCartUiState.Loading)
            val result = cartRepository.addProductToCart(
                productId = productId.toLong(),
                userId = userPreference.getUserId()
            )
            val state = when (result) {
                is Result.Success -> {
                    AddProductToCartUiState.Success
                }

                is Result.Failure -> {
                    AddProductToCartUiState.Error(result.error.errorMessage)
                }
            }
            _addProductToCartState.trySend(state)
        }
    }
}
