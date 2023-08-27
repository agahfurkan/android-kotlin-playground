package com.agah.furkan.domain.login

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.preferences.UserPreference
import com.agah.furkan.user.UserRepository
import com.agah.furkan.user.remote.model.request.UserLoginBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) {
    fun login(username: String, password: String): Flow<UiState> {
        return flow {
            emit(UiState.Loading)
            when (val result =
                userRepository.loginUser(UserLoginBody(password = password, username = username))) {
                is Result.Success -> {
                    userPreference.setToken(result.data.token!!)
                    userPreference.setUserId(result.data.userId!!)
                    emit(UiState.Success)
                }

                is Result.Failure -> {
                    emit(UiState.Failure(failureMessage = result.error.errorMessage))
                }
            }
        }
    }

    sealed class UiState {
        object Success : UiState()
        object Loading : UiState()
        data class Failure(val failureMessage: String) : UiState()
    }
}
