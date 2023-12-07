package com.agah.furkan.feature.product_detail_tabbed

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.agah.furkan.data.product.ProductDetail
import org.junit.Rule
import org.junit.Test

class ProductTabbedDetailScreenTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun ProductTabbedDetailScreenTest() {
        paparazzi.snapshot {
            ProductTabbedDetailScreen(productDetail = ProductDetailState.Success(
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
                        ), ProductDetail.Section(
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
                        ), ProductDetail.Review(
                            userName = "Vicky Daniels",
                            review = "dictum",
                            rating = 5,
                            date = "amet"
                        ), ProductDetail.Review(
                            userName = "Vicky Daniels",
                            review = "dictum",
                            rating = 5,
                            date = "amet"
                        )
                    )
                )
            ), initialPage = 1593, onBackButtonClicked = {})
        }
    }
}