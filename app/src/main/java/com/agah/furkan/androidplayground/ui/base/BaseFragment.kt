package com.agah.furkan.androidplayground.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    fun navigate(navDirections: NavDirections) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(android.R.anim.fade_in).setExitAnim(android.R.anim.fade_out)
            .setPopEnterAnim(android.R.anim.fade_in).setPopExitAnim(android.R.anim.fade_out)
        findNavController().navigate(navDirections, navBuilder.build())
    }
}
