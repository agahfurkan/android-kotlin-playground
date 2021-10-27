package com.agah.furkan.androidplayground.util

import com.agah.furkan.androidplayground.data.domain.model.Cart
import com.agah.furkan.androidplayground.data.domain.model.Category
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter

class CartListAdapter : GenericListAdapter<Cart>(ItemCartListBinding::class.java)
class MainCategoryListAdapter :
    GenericListAdapter<Category>(ItemMainCategoryListBinding::class.java)

class ProductListAdapter :
    GenericListAdapter<Product>(ItemProductListBinding::class.java) {
    interface ProductListListener : GenericListAdapterListener<Product> {
        fun onAddToCartClicked(item: Product)
    }
}
