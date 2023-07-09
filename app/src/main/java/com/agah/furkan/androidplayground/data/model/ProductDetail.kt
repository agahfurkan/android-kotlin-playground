package com.agah.furkan.androidplayground.data.model

data class ProductDetail(
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

fun List<ProductDetail.Review>.averageRating(): Float {
    return this.map { it.rating }.average().toFloat()
}

fun List<ProductDetail.Review>.totalReviewsByRating(): List<Triple<Int, Int, Float>> {
    val groupedByRatingList = this.groupBy { it.rating }
    val listMappedBySize = groupedByRatingList.mapValues { it.value.size }
    val totalReviewSize = listMappedBySize.values.sum()
    //rating, totalReviewSize, averageRating
    val tripleList = mutableListOf(
        Triple(1, 0, 0f),
        Triple(2, 0, 0f),
        Triple(3, 0, 0f),
        Triple(4, 0, 0f),
        Triple(5, 0, 0f)
    )
    listMappedBySize.forEach { item ->
        val matchedItem = tripleList.firstOrNull { it.first == item.key }
        tripleList.remove(matchedItem)
        tripleList.add(Triple(item.key, item.value, item.value / totalReviewSize.toFloat()))
    }
    return tripleList.sortedByDescending { it.first }
}