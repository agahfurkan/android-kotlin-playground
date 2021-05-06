package com.agah.furkan.androidplayground.util

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.data.web.model.response.ProductResponse
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.ui.adapter.recyclerview.GenericListAdapter
import com.bumptech.glide.Glide

object ViewHolderFactory {
    @Suppress("UNCHECKED_CAST")
    fun <T> create(
        parent: ViewGroup,
        viewHolderLayout: Class<*>,
        adapter: GenericListAdapter<T>
    ): GenericListAdapter.GenericViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = when (viewHolderLayout) {
            ItemCartListBinding::class.java -> {
                CartViewHolder(
                    ItemCartListBinding.inflate(
                        layoutInflater, parent, false
                    )
                )
            }
            ItemMainCategoryListBinding::class.java -> {
                CategoryViewHolder(
                    ItemMainCategoryListBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            ItemProductListBinding::class.java -> {
                ProductViewHolder(
                    ItemProductListBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            else -> {
                throw RuntimeException("err!")
            }
        } as GenericListAdapter.GenericViewHolder<T>
        viewHolder.adapter = adapter
        return viewHolder
    }

    class CartViewHolder(private val binding: ItemCartListBinding) :
        GenericListAdapter.GenericViewHolder<CartResponse.Cart>(binding) {
        override fun bind(item: CartResponse.Cart) {
            with(item) {
                Glide.with(binding.root).load(R.drawable.dummy_product_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.itemCartImage)//assume image path comes from web service
                binding.itemCartPrice.text = price.toString()
                if (discount != 0.0) {
                    binding.itemCartPrice.paintFlags =
                        binding.itemCartPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.itemCartPriceDiscounted.visibility = View.VISIBLE
                    binding.itemCartPriceDiscounted.text =
                        price.minus(price.times(item.discount).div(100)).toString()
                } else {
                    binding.itemCartPrice.paintFlags =
                        binding.itemCartPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    binding.itemCartPriceDiscounted.visibility = View.GONE
                }
                binding.itemCartTitle.text = productName
            }
            binding.itemCartRemoveButton.setOnClickListener {
                listAdapterListener?.onItemRemoveClicked(adapter, item)
            }
        }
    }

    class ProductViewHolder(private val binding: ItemProductListBinding) :
        GenericListAdapter.GenericViewHolder<ProductResponse.Product>(binding) {
        override fun bind(item: ProductResponse.Product) {
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
                (listAdapterListener as ProductListAdapter.ProductListListener).onAddToCartClicked(
                    item
                )
            }
            binding.root.setOnClickListener {
                listAdapterListener?.onItemClicked(adapter, item)
            }
        }
    }

    class CategoryViewHolder(private val binding: ItemMainCategoryListBinding) :
        GenericListAdapter.GenericViewHolder<CategoryResponse.Category>(binding) {
        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val binding = ItemMainCategoryListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CategoryViewHolder(binding)
            }
        }

        override fun bind(item: CategoryResponse.Category) {
            binding.itemMainCategoryName.text = item.categoryName
            binding.root.setOnClickListener {
                listAdapterListener?.onItemClicked(adapter, item)
            }
        }
    }
}