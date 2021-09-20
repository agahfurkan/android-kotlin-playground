package com.agah.furkan.androidplayground.ui.splash

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentSplashBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!
    private val splashFragmentVM by viewModels<SplashFragmentVM>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
