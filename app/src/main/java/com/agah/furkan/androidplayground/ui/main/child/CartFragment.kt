package com.agah.furkan.androidplayground.ui.main.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.databinding.FragmentCartBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment

class CartFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }
}