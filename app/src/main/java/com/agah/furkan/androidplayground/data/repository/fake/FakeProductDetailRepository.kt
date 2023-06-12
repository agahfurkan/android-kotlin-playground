package com.agah.furkan.androidplayground.data.repository.fake

import com.agah.furkan.androidplayground.data.model.ProductDetail
import com.agah.furkan.androidplayground.domain.repository.ProductDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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
                rating = 0.1f,
                date = "senectus"
            )
        )
    }
    return reviewList
}

