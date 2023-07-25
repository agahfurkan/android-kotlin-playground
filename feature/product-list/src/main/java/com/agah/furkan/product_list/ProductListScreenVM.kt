package com.agah.furkan.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.agah.furkan.domain.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListScreenVM @Inject constructor(
    productListPagingSource: com.agah.furkan.domain.product.ProductListPagingSource,
    state: SavedStateHandle
) : ViewModel() {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    val getProducts =
        productListPagingSource.getProductList(categoryId = state.get<Long>("categoryId") ?: 0L)
            .cachedIn(viewModelScope)
}
