package com.agah.furkan.androidplayground.util

import android.graphics.Paint
import android.view.View
import androidx.databinding.BindingAdapter
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.domain.model.result.Cart
import com.agah.furkan.androidplayground.view.CustomImageView
import com.agah.furkan.androidplayground.view.CustomTextView
import com.bumptech.glide.Glide

@BindingAdapter("imageSrc")
fun imageSrc(imageView: CustomImageView, image: String) {
    Glide.with(imageView.context)
        .load(image)
        .placeholder(R.drawable.placeholder_image)
        .into(imageView)
}

@BindingAdapter("discountedProductPrice")
fun discountedProductPrice(textView: CustomTextView, cart: Cart) {
    textView.apply {
        if (cart.discount != 0.0) {
            visibility = View.VISIBLE
            text = cart.price.discount(cart.discount).toString()
        } else {
            visibility = View.GONE
        }
    }
}

@BindingAdapter("productPrice")
fun productPrice(textView: CustomTextView, cart: Cart) {
    textView.apply {
        text = cart.price.toString()
        paintFlags = if (cart.discount != 0.0) {
            paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
