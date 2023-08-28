package com.agah.furkan.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.agah.furkan.feature.login.LoginRoute

const val loginRoute = "loginRoute"

fun NavController.navigateToLoginScreen(navOptions: NavOptions? = null) {
    navigate(loginRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    composable(loginRoute) {
        LoginRoute(onLoginSuccess = onLoginSuccess, onRegisterClicked = onRegisterClicked)
    }
}