package com.agah.furkan.data.category

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.category.model.CategoryDomainModel

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): Result<List<CategoryDomainModel>>
}
