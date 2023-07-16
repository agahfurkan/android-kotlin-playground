package com.agah.furkan.category

import com.agah.furkan.category.remote.model.response.CategoryResponse

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): com.agah.furkan.data.model.Result<List<CategoryResponse.Category>>
}
