package com.agah.furkan.domain.user

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.core.preferences.UserPreference
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
        return when (userRepository.validateToken(
            ValidateTokenRequest(
                token = userPreference.getToken().orEmpty()
            )
        )) {
            is DomainResult.Success -> {
                TokenValidationResult.Valid
            }

            is DomainResult.Failure -> {
                userPreference.clearAllData()
                TokenValidationResult.Invalid
            }
        }
    }
}