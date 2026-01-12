package com.agah.furkan.x

import android.view.LayoutInflater
import android.view.ViewGroup
import com.agah.furkan.core.view.recyclerview.BaseListAdapter
import com.agah.furkan.x.databinding.ItemStoreBinding
import com.agah.furkan.x.model.StoreListModel

class StoreListAdapter : BaseListAdapter<StoreListModel, ItemStoreBinding>() {
    override val layoutId: Int
        get() = R.layout.item_store

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ItemStoreBinding {
        return ItemStoreBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemStoreBinding, item: StoreListModel) {
        binding.apply {
        }
    }
}