package com.agah.furkan.androidplayground.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.agah.furkan.androidplayground.databinding.FragmentSplashBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import javax.inject.Inject

class SplashFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val splashFragmentVM by viewModels<SplashFragmentVM> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        splashFragmentVM.navigateEvent.observe(viewLifecycleOwner) {
            if (it) {
                //
            }
        }
    }
}