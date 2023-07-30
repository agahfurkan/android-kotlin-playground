package com.agah.furkan.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.agah.furkan.cart.CartScreenRoute
import com.agah.furkan.cart.remote.model.response.CartResponse

const val cartRoute = "cartRoute"

fun NavController.navigateToCartScreen(navOptions: NavOptions? = null) {
    this.navigate(cartRoute, navOptions)
}

fun NavGraphBuilder.cartScreen(
    cartList: Map<Long, List<CartResponse.Cart>>,
    onCartItemRemoved: (Long) -> Unit,
    removeProductFromCartClicked: (Long) -> Unit,
    addAdditionalProductClicked: (Int) -> Unit
) {
    composable(route = cartRoute) {
        CartScreenRoute(
            cartList,
            onCartItemRemoved,
            removeProductFromCartClicked,
            addAdditionalProductClicked
        )
    }
}
