package com.agah.furkan.core.view.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.core.util.ext.dp
import com.agah.furkan.core.view.recyclerview.decoration.ItemHorizontalSpaceDecoration
import com.agah.furkan.core.view.recyclerview.decoration.ItemVerticalSpaceDecoration

@BindingAdapter("app:itemsVerticalSpace", "app:applyToFirstAndLastItem", requireAll = false)
fun itemsVerticalSpace(
    recyclerView: RecyclerView,
    space: Float,
    applyToFirstAndLastItem: Boolean = false
) {
    recyclerView.addItemDecoration(
        ItemVerticalSpaceDecoration(
            verticalSpaceHeight = space.dp,
            applyToFirstAndLastItem = applyToFirstAndLastItem
        )
    )
}

@BindingAdapter("app:itemsHorizontalSpace")
fun itemsHorizontalSpace(recyclerView: RecyclerView, space: Float) {
    recyclerView.addItemDecoration(ItemHorizontalSpaceDecoration(space.dp))
}
