package com.agah.furkan.product_detail_tabbed

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)