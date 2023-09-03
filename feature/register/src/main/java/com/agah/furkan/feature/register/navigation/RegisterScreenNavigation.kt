package com.agah.furkan.feature.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.feature.register.RegisterRoute

const val registerRoute = "registerRoute"

fun NavController.navigateToRegisterScreen() {
    navigate(registerRoute)
}

fun NavGraphBuilder.registerScreen(onRegisterSuccess: () -> Unit) {
    composable(registerRoute) {
        RegisterRoute(onRegisterSuccess)
    }
}