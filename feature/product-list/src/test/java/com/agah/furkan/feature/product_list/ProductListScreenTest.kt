package com.agah.furkan.feature.product_list

import androidx.paging.compose.collectAsLazyPagingItems
import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class ProductListScreenTest : PaparazziTest() {

    @Test
    fun productListScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            ProductListScreen(
                productList = DummyDataGenerator.generateDummyData().collectAsLazyPagingItems(),
                itemClicked = {},
                onBackButtonClicked = {},
                newProductAddedToCart = {}
            )
        }
    }
}
