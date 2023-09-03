package com.agah.furkan.feature.product_detail_tabbed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agah.furkan.feature.product_detail_tabbed.ProductDetailTabbedRoute

internal const val ARG_PRODUCT_ID = "productId"
internal const val ARG_INITIAL_PAGE = "initialPage"
const val productDetailTabbedRoute = "productDetailTabbedRoute/{$ARG_PRODUCT_ID}/{$ARG_INITIAL_PAGE}"

fun NavController.navigateToProductDetailTabbed(productId: Long, initialPage: Int) {
    navigate(
        productDetailTabbedRoute.replace("{$ARG_PRODUCT_ID}", productId.toString())
            .replace("{$ARG_INITIAL_PAGE}", initialPage.toString())
    )
}

fun NavGraphBuilder.productDetailTabbedScreen(
    onBackButtonClicked: () -> Unit,
) {
    composable(
        route = productDetailTabbedRoute,
        arguments = listOf(
            navArgument(ARG_PRODUCT_ID) { type = NavType.LongType },
            navArgument(ARG_INITIAL_PAGE) { type = NavType.IntType })
    ) {
        ProductDetailTabbedRoute(
            onBackButtonClicked = onBackButtonClicked,
            initialPage = it.arguments?.getInt(ARG_INITIAL_PAGE) ?: 0,
        )
    }
}