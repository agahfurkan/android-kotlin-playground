package com.agah.furkan.category_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.agah.furkan.category_list.CategoryListRoute

const val categoryListRoute = "categoryListRoute"

fun NavController.navigateToCategoryList(navOptions: NavOptions? = null) {
    this.navigate(categoryListRoute, navOptions)
}

fun NavGraphBuilder.categoryListScreen(onCategoryClicked: (Long) -> Unit) {
    composable(categoryListRoute) {
        CategoryListRoute(onCategoryClicked = onCategoryClicked)
    }

}