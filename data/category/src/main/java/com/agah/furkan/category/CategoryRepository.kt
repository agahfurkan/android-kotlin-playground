package com.agah.furkan.category

import com.agah.furkan.category.remote.model.response.CategoryResponse
import com.agah.furkan.core.data.model.Result

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): Result<List<CategoryResponse.Category>>
}
