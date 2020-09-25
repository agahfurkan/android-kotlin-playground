package com.agah.furkan.androidplayground.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.agah.furkan.androidplayground.di.InjectableFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {
    override fun onAttach(context: Context) {
        if (this is InjectableFragment) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }
}
