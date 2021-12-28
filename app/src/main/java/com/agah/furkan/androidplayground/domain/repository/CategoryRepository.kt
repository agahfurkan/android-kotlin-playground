package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.result.Category

interface CategoryRepository {
    suspend fun fetchMainProductCategories(): Result<List<Category>>
}
