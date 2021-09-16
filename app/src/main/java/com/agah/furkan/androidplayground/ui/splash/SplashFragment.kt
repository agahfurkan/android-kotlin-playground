package com.agah.furkan.androidplayground.ui.splash

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.databinding.FragmentSplashBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!
    private val splashFragmentVM by viewModels<SplashFragmentVM>()

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
        splashFragmentVM.isTokenValid.observe(viewLifecycleOwner) {
            when (it) {
                true -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
                }
                false -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
