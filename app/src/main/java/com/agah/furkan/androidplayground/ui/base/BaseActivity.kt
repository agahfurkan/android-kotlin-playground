package com.agah.furkan.androidplayground.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections

abstract class BaseActivity : AppCompatActivity() {
    abstract val navController: NavController
    abstract fun navigate(navDirections: NavDirections)
    abstract fun navigateUp()
}
