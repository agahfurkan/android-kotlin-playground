package com.agah.furkan.androidplayground.ui.cart

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.base.BaseListAdapter
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.domain.model.result.Cart

class CartListAdapter(private val cartListCallback: CartListCallback) :
    BaseListAdapter<ItemCartListBinding, Cart>() {
    override val layoutRes: Int
        get() = R.layout.item_cart_list

    override fun bind(binding: ItemCartListBinding, item: Cart) {
        binding.callback = cartListCallback
        binding.item = item
        binding.executePendingBindings()
    }
}

interface CartListCallback {
    fun onRemoveClick(item: Cart)
}
