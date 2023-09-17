package com.agah.furkan.feature.category_list.model

internal sealed class CategoryListUiState {
    data class Success(val data: List<Category>) : CategoryListUiState()
    object Loading : CategoryListUiState()
    data class Error(val errorMessage: String) : CategoryListUiState()
}