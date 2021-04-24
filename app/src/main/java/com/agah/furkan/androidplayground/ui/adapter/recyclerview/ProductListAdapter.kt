package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil
import com.bumptech.glide.Glide


class ProductListAdapter(private val productAdapterListener: ProductAdapterListener) :
    ListAdapter<ProductResponse.Product, ProductListAdapter.ProductViewHolder>(GenericDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position), productAdapterListener)
    }

    class ProductViewHolder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponse.Product, productAdapterListener: ProductAdapterListener) {
            with(item) {
                binding.itemProductTitle.text = productName
                binding.itemProductPrice.text = price.toString()
                Glide.with(binding.root).load(R.drawable.dummy_product_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.itemProductImage)//assume image path comes from web service
                if (discount != 0.0) {
                    binding.itemProductPrice.paintFlags =
                        binding.itemProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.itemProductPriceDiscounted.visibility = View.VISIBLE
                    binding.itemProductPriceDiscounted.text =
                        price.minus(price.times(item.discount).div(100)).toString()
                } else {
                    binding.itemProductPrice.paintFlags =
                        binding.itemProductPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    binding.itemProductPriceDiscounted.visibility = View.GONE
                }
            }
            binding.itemProductAddToCartButton.setOnClickListener {
                productAdapterListener.onAddToCartClicked(item)
            }
            binding.root.setOnClickListener {
                productAdapterListener.onProductItemClicked(item)
            }
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

    interface ProductAdapterListener {
        fun onProductItemClicked(item: ProductResponse.Product)
        fun onAddToCartClicked(item: ProductResponse.Product)
    }
}