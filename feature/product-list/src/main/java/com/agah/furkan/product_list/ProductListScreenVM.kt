package com.agah.furkan.product_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.agah.furkan.domain.product.Product
import com.agah.furkan.product_list.navigation.ARG_CATEGORY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProductListScreenVM @Inject constructor(
    productListPagingSource: com.agah.furkan.domain.product.ProductListPagingSource,
    state: SavedStateHandle
) : ViewModel() {

    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    val getProducts =
        productListPagingSource.getProductList(categoryId = state.get<Long>(ARG_CATEGORY_ID) ?: 0L)
            .cachedIn(viewModelScope)
}
