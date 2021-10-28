package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.base.ListModel
import com.agah.furkan.androidplayground.data.domain.model.Announcement
import com.agah.furkan.androidplayground.data.domain.model.Cart
import com.agah.furkan.androidplayground.data.domain.model.Category
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.databinding.ItemAnnouncementListBinding
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.bumptech.glide.Glide

object ViewHolderFactory {
    @Suppress("UNCHECKED_CAST")
    fun <T : ListModel> create(
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
            ItemAnnouncementListBinding::class.java -> {
                AnnouncementViewHolder(
                    ItemAnnouncementListBinding.inflate(
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
        GenericListAdapter.GenericViewHolder<Cart>(binding) {
        override fun bind(item: Cart) {
            with(item) {
                Glide.with(binding.root).load(R.drawable.placeholder_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.itemCartImage) // assume image path comes from web service
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
        GenericListAdapter.GenericViewHolder<Product>(binding) {
        override fun bind(item: Product) {
            with(item) {
                binding.itemProductTitle.text = productName
                binding.itemProductPrice.text = price.toString()
                Glide.with(binding.root).load(R.drawable.placeholder_image)
                    .placeholder(R.drawable.placeholder_image)
                    .into(binding.itemProductImage) // assume image path comes from web service
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
        GenericListAdapter.GenericViewHolder<Category>(binding) {
        override fun bind(item: Category) {
            binding.itemMainCategoryName.text = item.categoryName
            binding.root.setOnClickListener {
                listAdapterListener?.onItemClicked(adapter, item)
            }
        }
    }

    class AnnouncementViewHolder(private val binding: ItemAnnouncementListBinding) :
        GenericListAdapter.GenericViewHolder<Announcement>(binding) {
        override fun bind(item: Announcement) {
            binding.root.setOnClickListener {
                listAdapterListener?.onItemClicked(adapter, item)
            }
        }
    }
}
