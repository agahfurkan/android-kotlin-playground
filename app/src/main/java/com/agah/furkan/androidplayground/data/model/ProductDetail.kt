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
    data class Review(val userName: String, val review: String, val rating: Float, val date: String)
}