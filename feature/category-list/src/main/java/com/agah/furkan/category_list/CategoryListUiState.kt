package com.agah.furkan.category_list

import com.agah.furkan.category.remote.model.response.CategoryResponse

sealed class CategoryListUiState {
    data class Success(val data: List<CategoryResponse.Category>) : CategoryListUiState()
    object Loading : CategoryListUiState()
    data class Error(val errorMessage: String) : CategoryListUiState()
}