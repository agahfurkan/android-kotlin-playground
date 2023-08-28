package com.agah.furkan.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.feature.profile.ProfileRoute

const val profileRoute = "profileRoute"

fun NavController.navigateToProfileScreen() {
    navigate(profileRoute)
}

fun NavGraphBuilder.profileScreen(onLogoutButtonClicked: () -> Unit) {
    composable(profileRoute) {
        ProfileRoute(onLogoutButtonClicked = onLogoutButtonClicked)
    }
}