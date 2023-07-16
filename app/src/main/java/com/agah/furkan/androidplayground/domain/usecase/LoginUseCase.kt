package com.agah.furkan.androidplayground.domain.usecase

import com.agah.furkan.androidplayground.data.mapper.toRequestModel
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.preferences.UserPreference
import com.agah.furkan.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) :
    BaseUseCase<UseCaseParams.UserLoginParams, LoginUseCase.UiState> {

    override suspend fun invoke(params: UseCaseParams.UserLoginParams): Flow<UiState> = flow {
        emit(UiState.Loading)
        when (val result = userRepository.loginUser(params.toRequestModel())) {
            is com.agah.furkan.data.model.Result.Success -> {
                userPreference.setToken(result.data.token!!)
                userPreference.setUserId(result.data.userId!!)
                emit(UiState.Success)
            }

            is com.agah.furkan.data.model.Result.Failure -> {
                emit(UiState.Failure(failureMessage = result.error.errorMessage))
            }
        }
    }

    sealed class UiState {
        object Success : UiState()
        object Loading : UiState()
        data class Failure(val failureMessage: String) : UiState()
    }
}
