package com.agah.furkan.data.product


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class FakeProductDetailRepository @Inject constructor() : ProductDetailRepository {
    override fun getProductDetails(): Flow<ProductDetail> {
        return flow<ProductDetail> {
            emit(
                ProductDetail(
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
                        ProductDetail.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetail.SectionDetail(name = "Diana Ford", value = "idque"),
                                ProductDetail.SectionDetail(name = "Marlin Henson", value = "quod")
                            )
                        ),
                        ProductDetail.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetail.SectionDetail(
                                    name = "Diana Ford",
                                    value = "idque"
                                ),
                                ProductDetail.SectionDetail(
                                    name = "Shawna Wiggins",
                                    value = "euismod"
                                ),
                                ProductDetail.SectionDetail(
                                    name = "Janie Marshall",
                                    value = "comprehensam"
                                )
                            )
                        ),
                        ProductDetail.Section(
                            sectionName = "Liza Olsen",
                            sectionContent = listOf(
                                ProductDetail.SectionDetail(
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


fun generateFakeReviewList(): List<ProductDetail.Review> {
    val reviewList = mutableListOf<ProductDetail.Review>()
    for (i in 0..9) {
        reviewList.add(
            ProductDetail.Review(
                userName = "Tracie Richmond",
                review = "accommodare",
                rating = Random.nextInt(1, 6),
                date = "senectus"
            )
        )
    }
    return reviewList
}

