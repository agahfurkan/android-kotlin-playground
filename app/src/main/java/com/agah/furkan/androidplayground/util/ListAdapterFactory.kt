package com.agah.furkan.androidplayground.util

import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter

class CartListAdapter : GenericListAdapter<CartResponse.Cart>(ItemCartListBinding::class.java)
class MainCategoryListAdapter :
    GenericListAdapter<CategoryResponse.Category>(ItemMainCategoryListBinding::class.java)

class ProductListAdapter :
    GenericListAdapter<ProductResponse.Product>(ItemProductListBinding::class.java) {
    interface ProductListListener : GenericListAdapterListener<ProductResponse.Product> {
        fun onAddToCartClicked(item: ProductResponse.Product)
    }
}
