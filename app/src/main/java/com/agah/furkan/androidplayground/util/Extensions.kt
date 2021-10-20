package com.agah.furkan.androidplayground.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.TransitionManager

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showLongToast(message: String) {
    context?.showLongToast(message)
}

val EditText.textValue: String
    get() = this.text.toString()

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.beginFadeTransition(viewGroup: ViewGroup) {
    val transition = Fade().apply {
        addTarget(this@beginFadeTransition)
    }
    TransitionManager.beginDelayedTransition(viewGroup, transition)
}
