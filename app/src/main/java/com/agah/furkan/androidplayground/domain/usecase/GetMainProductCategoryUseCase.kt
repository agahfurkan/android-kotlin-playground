package com.agah.furkan.androidplayground.domain.usecase

import com.agah.furkan.androidplayground.data.mapper.toDomainModel
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.category.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMainProductCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    fun getProductCategories(): Flow<UiState> {
        return flow {
            emit(UiState.Loading)
            when (val result = categoryRepository.fetchMainProductCategories()) {
                is com.agah.furkan.data.model.Result.Success -> {
                    emit(UiState.Success(categoryList = result.data.map { it.toDomainModel() }))
                }

                is com.agah.furkan.data.model.Result.Failure -> {
                    emit(UiState.Failure(failureMessage = result.error.errorMessage))
                }
            }
        }
    }

    sealed class UiState {
        data class Success(val categoryList: List<Category>) : UiState()
        object Loading : UiState()
        data class Failure(val failureMessage: String) : UiState()
    }
}
