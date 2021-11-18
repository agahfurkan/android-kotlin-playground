package com.agah.furkan.androidplayground.ui.productlist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.domain.model.Product
import com.agah.furkan.androidplayground.databinding.ItemProductListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil
import com.bumptech.glide.Glide

class ProductPagingAdapter :
    PagingDataAdapter<Product, ProductPagingAdapter.ProductListViewHolder>(GenericDiffUtil()) {
    var mListAdapterListener: ProductListListener? = null

    interface ProductListListener {
        fun onAddToCartClicked(item: Product)
        fun onItemClicked(item: Product)
    }

    class ProductListViewHolder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, mListAdapterListener: ProductListListener?) {
            with(product) {
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
                        price.minus(price.times(discount).div(100)).toString()
                } else {
                    binding.itemProductPrice.paintFlags =
                        binding.itemProductPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    binding.itemProductPriceDiscounted.visibility = View.GONE
                }
            }
            binding.itemProductAddToCartButton.setOnClickListener {
                mListAdapterListener?.onAddToCartClicked(product)
            }
            binding.root.setOnClickListener {
                mListAdapterListener?.onItemClicked(item = product)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ProductListViewHolder {
                return ProductListViewHolder(
                    ItemProductListBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ),
                        parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(product = item, mListAdapterListener = mListAdapterListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder.from(parent)
    }
}
