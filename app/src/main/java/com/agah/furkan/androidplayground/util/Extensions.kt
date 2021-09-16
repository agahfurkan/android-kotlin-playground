package com.agah.furkan.androidplayground.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(message: String) {
    context?.showLongToast(message)
}
