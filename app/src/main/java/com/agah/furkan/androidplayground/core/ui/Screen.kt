package com.agah.furkan.androidplayground.core.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(var title: String, var route: String) {
    object Home : Screen("Home", "home")
    object Profile : Screen("Profile", "profile")
    object SecondModule : Screen("Second Module", "second_module")
    object Splash : Screen("Splash", "splash")
    object Search : Screen("Search", "search")
    object ProductList : Screen("Product List", "productList/{categoryId}") {
        fun createRoute(categoryId: Long) = "productList/$categoryId"
        fun getArgs() = listOf(navArgument("categoryId") { type = NavType.LongType })
    }

    object ProductDetail : Screen("Product Detail", "productDetail/{productId}") {
        fun createRoute(productId: Long) = "productDetail/$productId"
        fun getArgs() = listOf(navArgument("productId") { type = NavType.LongType })
    }

    object Login : Screen("Login", "login")
    object Register : Screen("Registe", "register")
    object ProductDetailTabbed :
        Screen("Product Detail", "product_detail_tabbed/{productId}/{initialPage}") {
        fun createRoute(productId: Long, initialPage: Int) =
            "product_detail_tabbed/$productId/$initialPage"

        fun getArgs() = listOf(navArgument("productId") { type = NavType.LongType },
            navArgument("initialPage") { type = NavType.IntType })
    }
}