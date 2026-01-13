package com.agah.furkan.domain.category

import com.agah.furkan.core.domain.model.DomainResult

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): DomainResult<List<Category>>
}

