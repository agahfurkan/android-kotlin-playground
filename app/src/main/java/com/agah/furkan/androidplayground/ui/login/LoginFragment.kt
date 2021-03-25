package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment

class LoginFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}