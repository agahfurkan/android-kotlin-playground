package com.agah.furkan.androidplayground.domain.usecase

import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UserLoginParams
import com.agah.furkan.androidplayground.domain.repository.UserRepository
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) :
    BaseUseCase<UserLoginParams, LoginUseCase.UiState> {

    override suspend fun invoke(params: UserLoginParams): Flow<UiState> = flow {
        emit(UiState.Loading)
        when (val result = userRepository.loginUser(params)) {
            is Result.Success -> {
                SharedPrefUtil.setToken(result.data.token)
                SharedPrefUtil.setUserid(result.data.userId)
                emit(UiState.Success)
            }
            is Result.Failure -> {
                emit(UiState.Fail(failureMessage = result.error.errorMessage))
            }
        }
    }

    sealed class UiState {
        object Success : UiState()
        object Loading : UiState()
        data class Fail(val failureMessage: String) : UiState()
    }
}
