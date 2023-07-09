package com.agah.furkan.androidplayground.ui.productcategory

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.agah.furkan.androidplayground.domain.model.result.Category

class CategoryPreviewParameterProvider : PreviewParameterProvider<List<Category>> {
    override val values: Sequence<List<Category>>
        get() = sequenceOf(
            listOf<Category>(
                Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                ), Category(
                    categoryId = 7931,
                    categoryName = "Marcia Waters"
                )
            )
        )
}