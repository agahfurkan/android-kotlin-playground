package com.agah.furkan.androidplayground.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.core.ui.Screen
import com.agah.furkan.cart.navigation.cartRoute
import com.agah.furkan.category_list.navigation.categoryListRoute
import com.agah.furkan.home.navigation.homeRoute
import com.agah.furkan.profile.navigation.profileRoute

sealed class BottomNavItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: String
) {
    object Home :
        BottomNavItem(R.string.bottom_nav_home, R.drawable.ic_round_home, homeRoute)

    object Categories :
        BottomNavItem(R.string.bottom_nav_categories, R.drawable.ic_grid, categoryListRoute)

    object Cart : BottomNavItem(R.string.bottom_nav_cart, R.drawable.ic_cart, cartRoute)
    object Profile :
        BottomNavItem(R.string.bottom_nav_profile, R.drawable.ic_person, profileRoute)

    object SecondModule :
        BottomNavItem(
            R.string.bottom_nav_second_module,
            R.drawable.ic_star,
            Screen.SecondModule.route
        )

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