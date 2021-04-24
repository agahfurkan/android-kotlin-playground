package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil

class ProductListAdapter :
    ListAdapter<ProductResponse, ProductListAdapter.ProductViewHolder>(GenericDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse) {
            binding.itemProductTitle.text = item.productName
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val binding = ItemProductListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ProductViewHolder(binding)
            }
        }
    }
}