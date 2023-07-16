package com.agah.furkan.category.remote

import com.agah.furkan.category.remote.model.response.CategoryResponse
import retrofit2.http.GET

interface CategoryService {

    @GET("Category/GetAllCategories")
    suspend fun fetchCategories(): CategoryResponse
}
