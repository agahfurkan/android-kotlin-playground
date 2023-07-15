package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.data.model.Result
import com.agah.furkan.androidplayground.domain.model.result.Category

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): com.agah.furkan.data.model.Result<List<Category>>
}
