package com.agah.furkan.core.view.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemHorizontalSpaceDecoration(
    private val verticalSpaceHeight: Float
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.apply {
            val space = verticalSpaceHeight.toInt()
            left = space
            right = space
        }
    }
}
