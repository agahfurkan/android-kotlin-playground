package com.agah.furkan.feature.category_list

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.agah.furkan.feature.category_list.model.Category
import com.agah.furkan.feature.category_list.model.CategoryListUiState
import org.junit.Rule
import org.junit.Test

class CategoryListScreenTest {
    @get: Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    @Test
    fun cartScreenSnapshot() {
        paparazzi.snapshot {
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