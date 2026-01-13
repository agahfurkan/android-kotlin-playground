package com.agah.furkan.data.category

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.data.category.model.CategoryDomainModel

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): DomainResult<List<CategoryDomainModel>>
}
