package com.agah.furkan.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.agah.furkan.search.SearchRoute

const val searchRoute = "searchRoute"

fun NavController.navigateToSearchScreen() {
    navigate(searchRoute)
}

fun NavGraphBuilder.searchScreen(onBackPress: () -> Unit) {
    composable(searchRoute) {
        SearchRoute(onBackPress)
    }
}