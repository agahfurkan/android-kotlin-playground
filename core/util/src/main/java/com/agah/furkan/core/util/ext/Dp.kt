package com.agah.furkan.core.util.ext

import android.content.res.Resources

fun Float.dp(): Float {
    val density = Resources.getSystem().displayMetrics.density
    return this * density
}

val Float.dp: Float
    get() = dp()

val Int.dp: Float
    get() = toFloat().dp()

val Double.dp: Float
    get() = toFloat().dp()