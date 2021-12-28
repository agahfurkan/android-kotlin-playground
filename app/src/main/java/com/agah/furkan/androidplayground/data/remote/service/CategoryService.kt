package com.agah.furkan.androidplayground.data.remote.service

import com.agah.furkan.androidplayground.data.remote.model.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface CategoryService {

    @GET("Category/GetAllCategories")
    suspend fun fetchCategories(@HeaderMap header: HashMap<String, String>): CategoryResponse
}
