package com.agah.furkan.product_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agah.furkan.product_list.ProductListScreenRoute

internal const val ARG_CATEGORY_ID = "categoryId"
const val productListRoute = "productListRoute/{$ARG_CATEGORY_ID}"

fun NavController.navigateToProductListScreen(categoryId: Long) {
    navigate(productListRoute.replace("{$ARG_CATEGORY_ID}", categoryId.toString()))
}

fun NavGraphBuilder.productListScreen(
    itemClicked: (productId: Long) -> Unit,
    onBackButtonClicked: () -> Unit,
    addToCartClicked: (productId: Int) -> Unit,
) {
    composable(
        productListRoute,
        arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
    ) {
        ProductListScreenRoute(
            itemClicked = itemClicked,
            onBackButtonClicked = onBackButtonClicked,
            addToCartClicked = addToCartClicked
        )
    }
}