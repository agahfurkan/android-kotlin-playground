package com.agah.furkan.androidplayground.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment {
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)
    constructor() : super()
}
