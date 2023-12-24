package com.agah.furkan.feature.product_detail

import com.agah.furkan.core.test.PaparazziTest
import org.junit.Test

class ProductDetailScreenTest : PaparazziTest() {

    @Test
    fun productDetailScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            ProductDetailScreen(
                productState = ProductDetailUiState.Success(
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
                onAddToCartClicked = {}
            )
        }
    }
}
