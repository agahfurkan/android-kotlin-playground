package com.agah.furkan.androidplayground.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.agah.furkan.androidplayground.R
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class CustomEditText : AppCompatEditText {
    constructor(context: Context) : super(context) {
        initView(context = context, attrs = null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context = context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context = context, attrs = attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomEditText,
            0, 0
        ).apply {
            try {
                val strokeWidth = getDimensionPixelSize(R.styleable.CustomEditText_strokeWidth, 0)
                val cornerRadius = getDimensionPixelSize(R.styleable.CustomEditText_cornerRadius, 0)
                val shapeApp =
                    ShapeAppearanceModel().toBuilder().setAllCornerSizes(cornerRadius.toFloat())
                        .build()
                val shape = MaterialShapeDrawable(shapeApp).apply {
                    this.strokeWidth = strokeWidth.toFloat()
                    backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
                }
                background = shape
            } finally {
                recycle()
            }
        }
    }
}
