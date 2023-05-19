package com.agah.furkan.androidplayground.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.launchAndCollectIn
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment(null) {
    private val splashFragmentVM by viewModels<SplashFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initObservers()
        return ComposeView(requireContext()).apply {
            setContent {
                SplashScreen()
            }
        }
    }

    private fun initObservers() {
        splashFragmentVM.isTokenValid.launchAndCollectIn(viewLifecycleOwner) {
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

@Composable
@Preview
fun SplashScreen() {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    AppTheme {
        LottieAnimation(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }
}
