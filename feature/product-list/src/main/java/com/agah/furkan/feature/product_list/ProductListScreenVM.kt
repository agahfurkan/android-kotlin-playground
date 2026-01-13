package com.agah.furkan.feature.product_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.cart.AddProductToCartUseCase
import com.agah.furkan.feature.product_list.navigation.ARG_CATEGORY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductListScreenVM @Inject constructor(
    state: SavedStateHandle,
    productListPagingSource: com.agah.furkan.domain.product.ProductListPagingSource,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : ViewModel() {
    val getProducts =
        productListPagingSource.getProductList(categoryId = state.get<Long>(ARG_CATEGORY_ID) ?: 0L)
            .cachedIn(viewModelScope)

    private val _addProductToCartState =
        Channel<AddProductToCartUiState>(capacity = Channel.BUFFERED)
    val addProductToCartState = _addProductToCartState.receiveAsFlow()

    fun addProductToCart(productId: Int) {
        viewModelScope.launch {
            _addProductToCartState.trySend(AddProductToCartUiState.Loading)
            val result = addProductToCartUseCase(productId = productId.toLong())
            val state = when (result) {
                is DomainResult.Success -> {
                    AddProductToCartUiState.Success
                }

                is DomainResult.Failure -> {
                    AddProductToCartUiState.Error(result.error.message)
                }
            }
            _addProductToCartState.trySend(state)
        }
    }
}
