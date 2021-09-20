package com.agah.furkan.androidplayground.ui.splash

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentSplashBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val splashFragmentVM by viewModels<SplashFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(
                Drawable.createFromStream(
                    requireContext().assets.open("loading_image.gif"),
                    null
                )
            )
            .into(binding.splashImageview)
        initObservers()
    }

    private fun initObservers() {
        splashFragmentVM.isTokenValid.observe(viewLifecycleOwner, {
            val navDirection = when (it) {
                true -> {
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                }
                false -> {
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                }
            }
            findNavController().navigate(navDirection)
        })
    }
}
