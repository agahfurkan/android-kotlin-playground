package com.agah.furkan.core.view.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemVerticalSpaceDecoration(
    private val verticalSpaceHeight: Float,
    private val applyToFirstAndLastItem: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        val isFirstItem = position == 0
        val isLastItem = position == itemCount - 1

        if (!isLastItem) {
            outRect.bottom = verticalSpaceHeight.toInt()
        }

        if (applyToFirstAndLastItem) {
            if (isFirstItem) {
                outRect.top = verticalSpaceHeight.toInt()
            }
            if (isLastItem) {
                outRect.bottom = verticalSpaceHeight.toInt()
            }
        }
    }
}
