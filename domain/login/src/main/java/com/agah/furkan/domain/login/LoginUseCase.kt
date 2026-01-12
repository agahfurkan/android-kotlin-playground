package com.agah.furkan.domain.login

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.user.UserRepository
import com.agah.furkan.data.user.remote.model.request.UserLoginBody
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(username: String, password: String): Result<LoginResult> {
        return when (
            val result =
                userRepository.loginUser(UserLoginBody(password = password, username = username))
        ) {
            is Result.Success -> {
                userPreference.setToken(result.data.token!!)
                userPreference.setUserId(result.data.userId!!)
                Result.Success(
                    LoginResult(
                        token = result.data.token!!,
                        userId = result.data.userId!!,
                        message = result.data.message ?: ""
                    )
                )
            }

            is Result.Failure -> {
                Result.Failure(result.error)
            }
        }
    }
}
