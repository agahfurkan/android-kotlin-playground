package com.agah.furkan.feature.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.agah.furkan.data.cart.remote.model.response.CartResponse
import com.agah.furkan.feature.cart.CartRoute

const val cartRoute = "cartRoute"

fun NavController.navigateToCartScreen(navOptions: NavOptions? = null) {
    this.navigate(cartRoute, navOptions)
}

fun NavGraphBuilder.cartScreen(
    cartList: Map<Long, List<CartResponse.Cart>>,
    refreshCart: () -> Unit
) {
    composable(route = cartRoute) {
        CartRoute(
            cartList,
            refreshCart
        )
    }
}
