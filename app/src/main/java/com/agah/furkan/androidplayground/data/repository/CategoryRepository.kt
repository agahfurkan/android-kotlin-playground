package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryService: CategoryService) {

    suspend fun fetchMainProductCategories() = withContext(Dispatchers.IO) {
        categoryService.fetchCategories(RestConstants.getAuthHeader())
    }
}
