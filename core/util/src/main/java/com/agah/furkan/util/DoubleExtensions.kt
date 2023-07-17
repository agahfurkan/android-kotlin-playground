package com.agah.furkan.util

fun Double.discount(discountPercentage: Double): Double {
    return this.minus(this.times(discountPercentage).div(100))
}