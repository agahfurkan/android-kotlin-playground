package com.agah.furkan.feature.product_detail_tabbed

import androidx.compose.runtime.Composable

internal data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)