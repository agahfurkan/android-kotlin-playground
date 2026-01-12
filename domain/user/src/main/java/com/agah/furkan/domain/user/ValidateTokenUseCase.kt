package com.agah.furkan.domain.user

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.core.preferences.UserPreference
import com.agah.furkan.data.user.UserRepository
import com.agah.furkan.data.user.remote.model.request.ValidateTokenBody
import javax.inject.Inject


sealed class TokenValidationResult {
    object NoToken : TokenValidationResult()
    object Invalid : TokenValidationResult()
    object Valid : TokenValidationResult()
}

class ValidateTokenUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(): TokenValidationResult {
        val result = when (userRepository.validateToken(
            ValidateTokenBody(
                userPreference.getToken().orEmpty()
            )
        )) {
            is Result.Success<*> -> {
                TokenValidationResult.Valid
            }

            is Result.Failure<*> -> {
                userPreference.clearAllData()
                TokenValidationResult.Invalid
            }
        }
        return result
    }
}