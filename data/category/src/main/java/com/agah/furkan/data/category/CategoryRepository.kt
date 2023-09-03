package com.agah.furkan.data.category

import com.agah.furkan.data.category.remote.model.response.CategoryResponse
import com.agah.furkan.core.data.model.Result

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): Result<List<CategoryResponse.Category>>
}
