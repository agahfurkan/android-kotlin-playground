package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.response.CartResponse
import com.agah.furkan.androidplayground.databinding.ItemCartListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil
import com.bumptech.glide.Glide


class CartListAdapter(private val cartListAdapterListener: CartListAdapterListener) :
    ListAdapter<CartResponse.Cart, CartListAdapter.CartViewHolder>(GenericDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position), cartListAdapterListener)
    }

    class CartViewHolder(private val binding: ItemCartListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartResponse.Cart, cartListAdapterListener: CartListAdapterListener) {
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
                cartListAdapterListener.onRemoveButtonClicked(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CartViewHolder {
                val binding =
                    ItemCartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CartViewHolder(binding)
            }
        }
    }

    interface CartListAdapterListener {
        fun onRemoveButtonClicked(item: CartResponse.Cart)
    }
}