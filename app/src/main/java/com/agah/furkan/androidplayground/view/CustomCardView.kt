package com.agah.furkan.androidplayground.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

class CustomCardView : MaterialCardView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
