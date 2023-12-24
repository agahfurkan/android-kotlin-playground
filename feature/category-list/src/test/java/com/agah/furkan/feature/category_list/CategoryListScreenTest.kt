package com.agah.furkan.feature.category_list

import com.agah.furkan.core.test.PaparazziTest
import com.agah.furkan.feature.category_list.model.Category
import com.agah.furkan.feature.category_list.model.CategoryListUiState
import org.junit.Test

class CategoryListScreenTest : PaparazziTest() {

    @Test
    fun cartScreenSnapshot() {
        paparazzi.snapshotWithTheme {
            CategoryListScreen(
                state = CategoryListUiState.Success(
                    listOf(
                        Category(
                            categoryId = 1,
                            categoryName = "categoryName"
                        ),
                        Category(
                            categoryId = 2,
                            categoryName = "categoryName"
                        ),
                        Category(
                            categoryId = 3,
                            categoryName = "categoryName"
                        ),
                        Category(
                            categoryId = 4,
                            categoryName = "categoryName"
                        ),
                        Category(
                            categoryId = 5,
                            categoryName = "categoryName"
                        )
                    )
                ),
                onCategoryClicked = {}
            )
        }
    }
}
