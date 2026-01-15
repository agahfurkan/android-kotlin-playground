package com.agah.furkan.data.category.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.category.Category
import com.agah.furkan.domain.category.CategoryRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticCategoryRepository @Inject constructor() : CategoryRepository {

    override suspend fun fetchMainProductCategories(): DomainResult<List<Category>> {
        // Simulate network delay
        delay(400)

        val staticCategories = StaticCategoryData.getCategories()

        return DomainResult.Success(
            staticCategories.map { it.toDomainCategory() }
        )
    }

    // Mapper function
    private fun KmpCategory.toDomainCategory() = Category(
        categoryId = categoryId,
        categoryName = categoryName
    )
}

