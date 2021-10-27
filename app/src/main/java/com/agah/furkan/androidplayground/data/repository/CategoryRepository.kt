package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.domain.model.Category
import com.agah.furkan.androidplayground.data.web.RestConstants
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryService: CategoryService) {

    suspend fun fetchMainProductCategories(): List<Category> = withContext(Dispatchers.IO) {
        when (val response = categoryService.fetchCategories(RestConstants.getAuthHeader())) {
            is ApiSuccessResponse -> {
                response.data.categoryList?.map { it.toDomainModel() } ?: listOf()
            }
            is ApiErrorResponse -> {
                listOf()
            }
        }
    }
}
