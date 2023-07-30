package com.agah.furkan.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.register.RegisterRoute

const val registerRoute = "registerRoute"

fun NavController.navigateToRegisterScreen() {
    navigate(registerRoute)
}

fun NavGraphBuilder.registerScreen(onRegisterSuccess: () -> Unit) {
    composable(registerRoute) {
        RegisterRoute(onRegisterSuccess)
    }
}