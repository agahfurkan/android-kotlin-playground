package com.agah.furkan.androidplayground.util.ext

fun Double.discount(discountPercentage: Double): Double {
    return this.minus(this.times(discountPercentage).div(100))
}
