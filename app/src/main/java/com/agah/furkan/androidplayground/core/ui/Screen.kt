package com.agah.furkan.androidplayground.core.ui

sealed class Screen(var title: String, var route: String) {
    object Profile : Screen("Profile", "profile")
    object SecondModule : Screen("Second Module", "second_module")
    object Splash : Screen("Splash", "splash")
    object Search : Screen("Search", "search")
    object Login : Screen("Login", "login")
    object Register : Screen("Registe", "register")
}