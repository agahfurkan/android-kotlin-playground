package com.agah.furkan.androidplayground.ui.productlist

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListFragmentVM @Inject constructor(
    productRepository: ProductRepository,
    state: SavedStateHandle
) :
    ViewModel() {
    private var _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    val getProducts =
        productRepository.getProductList(categoryId = state.get<Long>("categoryId") ?: 0L)
            .cachedIn(viewModelScope)
}
