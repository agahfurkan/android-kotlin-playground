package com.agah.furkan.androidplayground.ui.main

import com.agah.furkan.androidplayground.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem("Home", R.drawable.ic_round_home, "home")
    object Categories : BottomNavItem("Categories", R.drawable.ic_grid, "categories")
    object Cart : BottomNavItem("Cart", R.drawable.ic_cart, "cart")
    object Profile : BottomNavItem("Profile", R.drawable.ic_person, "profile")
    object SecondModule : BottomNavItem("Second Module", R.drawable.ic_star, "second_module")

    companion object {
        fun getBottomNavItems() = listOf(
            Home,
            Categories,
            Cart,
            Profile,
            SecondModule
        )
    }
}