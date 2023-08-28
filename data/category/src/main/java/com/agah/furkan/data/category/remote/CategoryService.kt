package com.agah.furkan.data.category.remote

import com.agah.furkan.data.category.remote.model.response.CategoryResponse
import retrofit2.http.GET

interface CategoryService {

    @GET("Category/GetAllCategories")
    suspend fun fetchCategories(): CategoryResponse
}
