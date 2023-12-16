package com.agah.furkan.x

import com.agah.furkan.core.view.recyclerview.BaseListAdapter
import com.agah.furkan.x.databinding.ItemStoreBinding
import com.agah.furkan.x.model.StoreListModel

class StoreListAdapter : BaseListAdapter<StoreListModel, ItemStoreBinding>() {
    override val layoutId: Int
        get() = R.layout.item_store

    override fun bind(binding: ItemStoreBinding, item: StoreListModel) {
        binding.apply {
            this.item = item
            executePendingBindings()
        }
    }
}