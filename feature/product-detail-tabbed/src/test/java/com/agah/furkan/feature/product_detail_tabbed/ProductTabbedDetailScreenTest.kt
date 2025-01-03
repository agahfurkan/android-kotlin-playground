package com.agah.furkan.feature.product_detail_tabbed

import com.agah.furkan.core.test.PaparazziTest
import com.agah.furkan.data.product.ProductDetail
import org.junit.Test

class ProductTabbedDetailScreenTest : PaparazziTest() {

    @Test
    fun productTabbedDetailScreenSnapshotTest() {
        paparazzi.snapshotWithTheme {
            ProductTabbedDetailScreen(
                productDetail = ProductDetailState.Success(
                    data = ProductDetail(
                        productId = "propriae",
                        productName = "Rodger Barr",
                        description = "dui",
                        sections = listOf(
                            ProductDetail.Section(
                                sectionName = "Guadalupe Farley",
                                sectionContent = listOf(
                                    ProductDetail.SectionDetail(
                                        name = "Jose Aguirre",
                                        value = "impetus"
                                    )
                                )
                            ),
                            ProductDetail.Section(
                                sectionName = "Guadalupe Farley",
                                sectionContent = listOf(
                                    ProductDetail.SectionDetail(
                                        name = "Jose Aguirre",
                                        value = "impetus"
                                    )
                                )
                            )
                        ),
                        reviews = listOf(
                            ProductDetail.Review(
                                userName = "Vicky Daniels",
                                review = "dictum",
                                rating = 5,
                                date = "amet"
                            ),
                            ProductDetail.Review(
                                userName = "Vicky Daniels",
                                review = "dictum",
                                rating = 5,
                                date = "amet"
                            ),
                            ProductDetail.Review(
                                userName = "Vicky Daniels",
                                review = "dictum",
                                rating = 5,
                                date = "amet"
                            )
                        )
                    )
                ),
                initialPage = 1593,
                onBackButtonClicked = {}
            )
        }
    }
}
