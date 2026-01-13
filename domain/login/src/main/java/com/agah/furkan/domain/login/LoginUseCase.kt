package com.agah.furkan.domain.login

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.domain.user.LoginRequest
import com.agah.furkan.domain.user.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(username: String, password: String): DomainResult<LoginResult> {
        return when (
            val result =
                userRepository.loginUser(LoginRequest(password = password, username = username))
        ) {
            is DomainResult.Success -> {
                userPreference.setToken(result.data.token!!)
                userPreference.setUserId(result.data.userId!!)
                DomainResult.Success(
                    LoginResult(
                        token = result.data.token!!,
                        userId = result.data.userId!!,
                        message = result.data.message ?: ""
                    )
                )
            }

            is DomainResult.Failure -> {
                DomainResult.Failure(result.error)
            }
        }
    }
}
