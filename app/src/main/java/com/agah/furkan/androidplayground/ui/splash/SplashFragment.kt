package com.agah.furkan.androidplayground.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentSplashBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val splashFragmentVM by viewModels<SplashFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            splashFragmentVM.isTokenValid.collect {
                val navDirection = when (it) {
                    true -> {
                        SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    }
                    false -> {
                        SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                    }
                }
                findNavController().navigate(navDirection)
            }
        }
    }
}
