package com.agah.furkan.product_detail_tabbed.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agah.furkan.product_detail_tabbed.ProductDetailTabbedRoute

private const val ARG_PRODUCT_ID = "productId"
private const val ARG_INITIAL_PAGE = "initialPage"
const val productDetailTabbedRoute = "product_detail_tabbed/{$ARG_PRODUCT_ID}/{$ARG_INITIAL_PAGE}"

fun NavController.navigateToProductDetailTabbed(productId: Long, initialPage: Int) {
    navigate("product_detail_tabbed/$productId/$initialPage")
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