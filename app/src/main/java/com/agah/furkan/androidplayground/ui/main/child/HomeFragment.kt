package com.agah.furkan.androidplayground.ui.main.child

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentHomeBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)

    companion object {
        fun newInstance() = HomeFragment()
    }
}
