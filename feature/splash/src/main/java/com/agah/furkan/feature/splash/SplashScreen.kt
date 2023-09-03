package com.agah.furkan.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.util.launchAndCollectIn
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun SplashRoute(isTokenValid: (Boolean) -> Unit) {
    SplashScreen(isTokenValid = isTokenValid)
}

@Composable
private fun SplashScreen(
    viewModel: SplashScreenVM = hiltViewModel(),
    isTokenValid: (Boolean) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        viewModel.isTokenValid.launchAndCollectIn(lifecycleOwner) {
            isTokenValid(it)
        }
    }
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    AppTheme {
        LottieAnimation(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }
}
