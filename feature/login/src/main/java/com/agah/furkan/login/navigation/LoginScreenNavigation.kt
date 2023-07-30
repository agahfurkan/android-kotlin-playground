package com.agah.furkan.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.login.LoginScreenRoute

const val loginScreenRoute = "loginRoute"

fun NavController.navigateToLoginScreen() {
    navigate(loginScreenRoute)
}

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    composable(loginScreenRoute) {
        LoginScreenRoute(onLoginSuccess = onLoginSuccess, onRegisterClicked = onRegisterClicked)
    }
}