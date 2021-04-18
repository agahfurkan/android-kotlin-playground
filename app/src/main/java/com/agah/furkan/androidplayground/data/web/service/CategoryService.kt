package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import java.util.*

interface CategoryService {

    @GET("Category/getallcategories")
    suspend fun fetchCategories(@HeaderMap header: HashMap<String, String>): ApiResponse<List<CategoryResponse>>
}
