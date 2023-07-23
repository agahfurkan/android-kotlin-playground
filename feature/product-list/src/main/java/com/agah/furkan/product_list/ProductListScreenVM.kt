package com.agah.furkan.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.agah.furkan.product.remote.model.response.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListScreenVM @Inject constructor(
    productListPagingSource: ProductListPagingSource,
    state: SavedStateHandle
) : ViewModel() {

    private var _productList = MutableLiveData<List<ProductResponse.Product>>()
    val productList: LiveData<List<ProductResponse.Product>> get() = _productList
    val getProducts =
        productListPagingSource.getProductList(categoryId = state.get<Long>("categoryId") ?: 0L)
            .cachedIn(viewModelScope)
}
