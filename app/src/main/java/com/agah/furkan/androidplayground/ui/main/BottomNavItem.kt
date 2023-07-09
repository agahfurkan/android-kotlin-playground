package com.agah.furkan.androidplayground.ui.main

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.core.ui.Screen

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : BottomNavItem(Screen.Home.title, R.drawable.ic_round_home, Screen.Home.route)
    object Categories :
        BottomNavItem(Screen.Categories.title, R.drawable.ic_grid, Screen.Categories.route)

    object Cart : BottomNavItem(Screen.Cart.title, R.drawable.ic_cart, Screen.Cart.route)
    object Profile : BottomNavItem(Screen.Profile.title, R.drawable.ic_person, Screen.Profile.route)
    object SecondModule :
        BottomNavItem(Screen.SecondModule.title, R.drawable.ic_star, Screen.SecondModule.route)

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