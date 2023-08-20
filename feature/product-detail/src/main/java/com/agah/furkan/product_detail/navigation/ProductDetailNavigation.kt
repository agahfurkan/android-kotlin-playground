package com.agah.furkan.product_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agah.furkan.product_detail.ProductDetailRoute

internal const val ARG_PRODUCT_ID = "productId"
const val productDetailRoute = "productDetailRoute/{$ARG_PRODUCT_ID}"

fun NavController.navigateToProductDetail(productId: Long) {
    navigate(productDetailRoute.replace("{$ARG_PRODUCT_ID}", productId.toString()))
}

fun NavGraphBuilder.productDetailScreen(
    onBackButtonClicked: () -> Unit,
    onProductDetailClicked: (productId: Long) -> Unit,
    onProductDescriptionClicked: (productId: Long) -> Unit,
    onReviewsClicked: (productId: Long) -> Unit,
    onAllReviewsClicked: (productId: Long) -> Unit,
    newProductAddedToCart: () -> Unit
) {
    composable(
        route = productDetailRoute,
        arguments = listOf(navArgument("productId") { type = NavType.LongType })
    ) {
        ProductDetailRoute(
            onBackButtonClicked = onBackButtonClicked,
            onProductDetailClicked = onProductDetailClicked,
            onProductDescriptionClicked = onProductDescriptionClicked,
            onReviewsClicked = onReviewsClicked,
            onAllReviewsClicked = onAllReviewsClicked,
            newProductAddedToCart = newProductAddedToCart
        )
    }
}