package com.agah.furkan.androidplayground.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agah.furkan.androidplayground.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.refresh_lottie))
    Box(
        modifier = modifier
            .width(LocalConfiguration.current.screenWidthDp.dp / 3)
            .aspectRatio(1f)
    ) {
        LottieAnimation(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
        )
    }

}


@Composable
@Preview
fun LoadingPreview() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        LoadingState()
    }
}