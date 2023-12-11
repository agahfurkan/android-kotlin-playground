package com.agah.furkan.feature.product_list

import androidx.paging.compose.collectAsLazyPagingItems
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class ProductListScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun productListScreenTest() {
        paparazzi.snapshot {
            ProductListScreen(
                productList = DummyDataGenerator.generateDummyData().collectAsLazyPagingItems(),
                itemClicked = {},
                onBackButtonClicked = {},
                newProductAddedToCart = {})
        }
    }
}