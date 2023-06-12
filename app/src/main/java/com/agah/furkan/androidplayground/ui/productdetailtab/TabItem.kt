package com.agah.furkan.androidplayground.ui.productdetailtab

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)