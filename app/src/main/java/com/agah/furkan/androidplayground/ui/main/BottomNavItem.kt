package com.agah.furkan.androidplayground.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.agah.furkan.androidplayground.R
import com.agah.furkan.feature.cart.navigation.cartRoute
import com.agah.furkan.feature.category_list.navigation.categoryListRoute
import com.agah.furkan.feature.home.navigation.homeRoute
import com.agah.furkan.feature.profile.navigation.profileRoute

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
            "dummyRoute"
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