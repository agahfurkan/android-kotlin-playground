package com.agah.furkan.feature.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.data.category.CategoryRepository
import com.agah.furkan.feature.category_list.model.CategoryListUiState
import com.agah.furkan.feature.category_list.model.asUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CategoryListViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {
    private val _categoryList = MutableStateFlow<CategoryListUiState>(CategoryListUiState.Loading)
    val categoryList = _categoryList.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val result = categoryRepository.fetchMainProductCategories()
            val state = when (result) {
                is DomainResult.Success -> {
                    CategoryListUiState.Success(result.data.map { it.asUiModel() })
                }

                is DomainResult.Failure -> {
                    CategoryListUiState.Error(result.error.message)
                }
            }
            _categoryList.emit(state)
        }
    }
}