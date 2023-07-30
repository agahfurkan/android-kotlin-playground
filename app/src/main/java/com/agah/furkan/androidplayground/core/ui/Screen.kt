package com.agah.furkan.androidplayground.core.ui

sealed class Screen(var title: String, var route: String) {
    object SecondModule : Screen("Second Module", "second_module")
    object Splash : Screen("Splash", "splash")
    object Search : Screen("Search", "search")
    object Register : Screen("Registe", "register")
}