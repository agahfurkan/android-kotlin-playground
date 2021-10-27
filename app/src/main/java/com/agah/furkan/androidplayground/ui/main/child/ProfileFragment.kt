package com.agah.furkan.androidplayground.ui.main.child

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentProfileBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    companion object {
        fun newInstance() = ProfileFragment()
    }
}
