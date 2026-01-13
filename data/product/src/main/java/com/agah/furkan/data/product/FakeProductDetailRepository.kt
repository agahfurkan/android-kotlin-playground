package com.agah.furkan.data.product

import com.agah.furkan.domain.product.ProductDetailFlow
import com.agah.furkan.domain.product.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class FakeProductDetailRepository @Inject constructor() : ProductDetailRepository {
    override fun getProductDetails(): Flow<ProductDetailFlow> {
        return flow<ProductDetailFlow> {
            emit(
                ProductDetailFlow(
                    productId = "quaerendum",
                    productName = "Winfred Vasquez",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
                            " eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum " +
                            "dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt" +
                            " ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing" +
                            " elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                            " Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                            " tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit " +
                            "amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                            "labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur " +
                            "adipiscing elit, sed do eiusmod tempor incididunt ut labore et " +
                            "dolore magna aliqua.",
                    sections = listOf(
                        ProductDetailFlow.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetailFlow.SectionDetail(name = "Diana Ford", value = "idque"),
                                ProductDetailFlow.SectionDetail(name = "Marlin Henson", value = "quod")
                            )
                        ),
                        ProductDetailFlow.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetailFlow.SectionDetail(
                                    name = "Diana Ford",
                                    value = "idque"
                                ),
                                ProductDetailFlow.SectionDetail(
                                    name = "Shawna Wiggins",
                                    value = "euismod"
                                ),
                                ProductDetailFlow.SectionDetail(
                                    name = "Janie Marshall",
                                    value = "comprehensam"
                                )
                            )
                        ),
                        ProductDetailFlow.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetailFlow.SectionDetail(
                                    name = "Diana Ford",
                                    value = "idque"
                                )
                            )
                        )
                    ),
                    reviews = generateFakeReviewList()
                )
            )
        }
    }
}


fun generateFakeReviewList(): List<ProductDetailFlow.Review> {
    val reviewList = mutableListOf<ProductDetailFlow.Review>()
    for (i in 0..9) {
        reviewList.add(
            ProductDetailFlow.Review(
                userName = "Tracie Richmond",
                review = "accommodare",
                rating = Random.nextInt(1, 6),
                date = "senectus"
            )
        )
    }
    return reviewList
}

