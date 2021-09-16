package com.agah.furkan.androidplayground.view

import android.content.Context
import android.util.AttributeSet
import com.agah.furkan.androidplayground.R
import com.google.android.material.button.MaterialButton

class CustomButton : MaterialButton {
    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    ) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        this.isAllCaps = false
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomButton,
            0, 0
        ).apply {
            try {
                val removeInsets = getBoolean(R.styleable.CustomButton_removeInsets, false)
                if (removeInsets) {
                    this@CustomButton.insetBottom = 0
                    this@CustomButton.insetTop = 0
                }
            } finally {
                recycle()
            }
        }
    }
}
