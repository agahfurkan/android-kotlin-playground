package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.databinding.FragmentDiscoverBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment

class DiscoverFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }
}