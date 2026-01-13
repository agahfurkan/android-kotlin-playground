package com.agah.furkan.domain.product

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsFlowUseCase @Inject constructor(
    private val productDetailRepository: ProductDetailRepository
) {
    operator fun invoke(): Flow<ProductDetailFlow> {
        return productDetailRepository.getProductDetails()
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

