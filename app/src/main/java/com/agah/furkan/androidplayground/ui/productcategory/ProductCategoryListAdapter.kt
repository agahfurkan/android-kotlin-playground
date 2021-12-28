package com.agah.furkan.androidplayground.ui.productcategory

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.base.BaseListAdapter
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.domain.model.result.Category

class ProductCategoryListAdapter(private val productCategoryListCallback: ProductCategoryListCallback) :
    BaseListAdapter<ItemMainCategoryListBinding, Category>() {
    override val layoutRes: Int
        get() = R.layout.item_main_category_list

    override fun bind(binding: ItemMainCategoryListBinding, item: Category) {
        binding.apply {
            this.item = item
            callback = productCategoryListCallback
            executePendingBindings()
        }
    }
}

interface ProductCategoryListCallback {
    fun onItemClicked(item: Category)
}
