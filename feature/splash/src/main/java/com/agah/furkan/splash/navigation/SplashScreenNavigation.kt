package com.agah.furkan.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.splash.SplashRoute
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val splashRoute = "splashRoute"

fun NavController.navigateToSplashScreen() {
    navigate(splashRoute)
}

fun NavGraphBuilder.splashScreen(isTokenValid: (Boolean) -> Unit) {
    composable(splashRoute) {
        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.isStatusBarVisible = false
        SplashRoute(isTokenValid = isTokenValid)
    }
}