package com.agah.furkan.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.util.ext.launchAndCollectIn
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun SplashRoute(
    viewModel: SplashScreenVM = hiltViewModel(),
    isTokenValid: (Boolean) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        viewModel.isTokenValid.launchAndCollectIn(lifecycleOwner) {
            isTokenValid(it)
        }
    }

    SplashScreen()
}

@Composable
internal fun SplashScreen() {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_lottie))
    Box(modifier = Modifier.testTag("Splash Screen")) {
        LottieAnimation(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }
}
