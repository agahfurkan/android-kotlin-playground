package com.agah.furkan.androidplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.databinding.FragmentMainBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment

class MainFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}