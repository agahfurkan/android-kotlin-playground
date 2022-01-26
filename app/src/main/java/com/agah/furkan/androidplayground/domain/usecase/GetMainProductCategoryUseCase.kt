package com.agah.furkan.androidplayground.domain.usecase

import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.model.result.Category
import com.agah.furkan.androidplayground.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMainProductCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) :
    BaseUseCase<UseCaseParams.None, GetMainProductCategoryUseCase.UiState> {
    override suspend fun invoke(params: UseCaseParams.None): Flow<UiState> = flow {
        emit(UiState.Loading)
        when (val result = categoryRepository.fetchMainProductCategories()) {
            is Result.Success -> {
                emit(UiState.Success(categoryList = result.data))
            }
            is Result.Failure -> {
                emit(UiState.Failure(failureMessage = result.error.errorMessage))
            }
        }
    }

    sealed class UiState {
        data class Success(val categoryList: List<Category>) : UiState()
        object Loading : UiState()
        data class Failure(val failureMessage: String) : UiState()
    }
}
