package com.agah.furkan.androidplayground.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.agah.furkan.androidplayground.ui.MainActivity
import timber.log.Timber

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    constructor(@LayoutRes layoutRes: Int?) : this(0)

    open val toolbarType: ToolbarType = ToolbarType.None

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.confToolbar(toolbarType)
    }

    sealed class ToolbarType {
        object None : ToolbarType()
        class WithActionButtons(val enabledButton: List<ToolbarButton>) : ToolbarType()
        enum class ToolbarButton {
            BACK,
            DONE
        }
    }

    fun navigate(navDirections: NavDirections) =
        (activity as? BaseActivity)?.navigate(navDirections)

    fun navigateUp() =
        (activity as? BaseActivity)?.navigateUp()

    override fun onDestroy() {
        Timber.i("onDestroy:$this")
        super.onDestroy()
    }

    override fun onDestroyView() {
        Timber.i("onDestroyView:$this")
        super.onDestroyView()
    }
}
