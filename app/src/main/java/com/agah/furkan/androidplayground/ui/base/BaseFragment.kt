package com.agah.furkan.androidplayground.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.ui.MainActivity

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    open val toolbarType: ToolbarType = ToolbarType.NONE
    fun navigate(navDirections: NavDirections) {
        val navBuilder = NavOptions.Builder()
        navBuilder.setEnterAnim(android.R.anim.fade_in).setExitAnim(android.R.anim.fade_out)
            .setPopEnterAnim(android.R.anim.fade_in).setPopExitAnim(android.R.anim.fade_out)
        findNavController().navigate(navDirections, navBuilder.build())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null && activity is MainActivity) {
            (activity as MainActivity).confToolbar(toolbarType)
        }
    }

    enum class ToolbarType {
        NONE,
        BACK
    }
}
