package com.agah.furkan.category_list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.agah.furkan.data.category.remote.model.response.CategoryResponse.Category

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