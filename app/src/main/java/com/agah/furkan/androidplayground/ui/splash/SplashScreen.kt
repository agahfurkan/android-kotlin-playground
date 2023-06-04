package com.agah.furkan.androidplayground.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.launchAndCollectIn
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(viewModel: SplashScreenVM = hiltViewModel(), isTokenValid: (Boolean) -> Unit) {
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
