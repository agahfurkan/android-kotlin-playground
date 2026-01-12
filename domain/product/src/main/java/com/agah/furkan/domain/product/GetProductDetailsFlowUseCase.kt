package com.agah.furkan.domain.product

import com.agah.furkan.data.product.ProductDetailRepository
import com.agah.furkan.data.product.ProductDetail as DataProductDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailsFlowUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {
    operator fun invoke(): Flow<ProductDetailFlow> {
        return productDetailRepository.getProductDetails().map { it.toDomain() }
    }

    private fun DataProductDetail.toDomain(): ProductDetailFlow {
        return ProductDetailFlow(
            productId = productId,
            productName = productName,
            description = description,
            sections = sections.map { section ->
                ProductDetailFlow.Section(
                    sectionName = section.sectionName,
                    sectionContent = section.sectionContent.map { detail ->
                        ProductDetailFlow.SectionDetail(
                            name = detail.name,
                            value = detail.value
                        )
                    }
                )
            },
            reviews = reviews.map { review ->
                ProductDetailFlow.Review(
                    userName = review.userName,
                    review = review.review,
                    rating = review.rating,
                    date = review.date
                )
            }
        )
    }
}

data class ProductDetailFlow(
    val productId: String,
    val productName: String,
    val description: String,
    val sections: List<Section>,
    val reviews: List<Review>
) {
    data class Section(val sectionName: String, val sectionContent: List<SectionDetail>)
    data class SectionDetail(val name: String, val value: String)
    data class Review(val userName: String, val review: String, val rating: Int, val date: String)
}

fun List<ProductDetailFlow.Review>.averageRating(): Float {
    return this.map { it.rating }.average().toFloat()
}

fun List<ProductDetailFlow.Review>.totalReviewsByRating(): List<Triple<Int, Int, Float>> {
    val groupedByRatingList = this.groupBy { it.rating }
    val listMappedBySize = groupedByRatingList.mapValues { it.value.size }
    val totalReviewSize = listMappedBySize.values.sum()
    val tripleList = mutableListOf(
        Triple(1, 0, 0f),
        Triple(2, 0, 0f),
        Triple(3, 0, 0f),
        Triple(4, 0, 0f),
        Triple(5, 0, 0f)
    )
    listMappedBySize.forEach { item ->
        tripleList[item.key - 1] = Triple(
            item.key,
            item.value,
            item.value.toFloat() / totalReviewSize.toFloat()
        )
    }
    return tripleList
}

