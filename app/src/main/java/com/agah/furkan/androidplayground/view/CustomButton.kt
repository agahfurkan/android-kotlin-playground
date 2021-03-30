package com.agah.furkan.androidplayground.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class CustomButton : MaterialButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    )
}