package com.agah.furkan.feature.category_list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.agah.furkan.feature.category_list.model.Category

internal class CategoryPreviewParameterProvider : PreviewParameterProvider<List<Category>> {
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