package com.agah.furkan.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.agah.furkan.home.HomeScreenRoute
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val homeRoute = "homeRoute"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(onSearchFocused: () -> Unit) {
    composable(homeRoute) {
        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.isStatusBarVisible = true
        HomeScreenRoute(onSearchFocused)
    }
}