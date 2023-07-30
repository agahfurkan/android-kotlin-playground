package com.agah.furkan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agah.furkan.product_detail.ProductDetailRoute

const val productDetailRoute = "productDetail/{productId}"

fun NavController.navigateToProductDetail(productId: Long) {
    navigate("productDetail/$productId")
}

fun NavGraphBuilder.productDetailScreen(
    onBackButtonClicked: () -> Unit,
    onProductDetailClicked: (productId: Long) -> Unit,
    onProductDescriptionClicked: (productId: Long) -> Unit,
    onReviewsClicked: (productId: Long) -> Unit,
    onAllReviewsClicked: (productId: Long) -> Unit,
    onAddToCartClicked: (productId: Int) -> Unit
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
            onAddToCartClicked = onAddToCartClicked
        )
    }
}