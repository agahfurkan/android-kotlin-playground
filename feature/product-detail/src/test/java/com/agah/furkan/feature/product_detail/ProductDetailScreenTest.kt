package com.agah.furkan.feature.product_detail

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun productDetailScreenTest() {
        paparazzi.snapshot {
            ProductDetailScreen(productState = ProductDetailUiState.Success(
                productDetail = ProductDetail(
                    categoryId = 4284,
                    discount = 4.5,
                    picture = "imperdiet",
                    price = 6.7,
                    productDescription = "error",
                    productId = 3362,
                    productName = "Rena Hewitt"
                )
            ),
                onBackButtonClicked = {},
                onProductDetailClicked = {},
                onProductDescriptionClicked = {},
                onReviewsClicked = {},
                onAllReviewsClicked = {},
                onAddToCartClicked = {})
        }
    }
}