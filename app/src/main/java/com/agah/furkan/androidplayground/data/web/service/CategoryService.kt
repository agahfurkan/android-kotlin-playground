package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import java.util.*
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface CategoryService {

    @GET("Category/GetAllCategories")
    suspend fun fetchCategories(@HeaderMap header: HashMap<String, String>): ApiResponse<CategoryResponse>
}
