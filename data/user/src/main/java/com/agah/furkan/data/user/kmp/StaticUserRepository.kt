package com.agah.furkan.data.user.kmp

import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.user.LoginRequest
import com.agah.furkan.domain.user.LoginResponse
import com.agah.furkan.domain.user.RegisterRequest
import com.agah.furkan.domain.user.UserRepository
import com.agah.furkan.domain.user.ValidateTokenRequest
import kotlinx.coroutines.delay
import javax.inject.Inject

class StaticUserRepository @Inject constructor() : UserRepository {

    override suspend fun loginUser(request: LoginRequest): DomainResult<LoginResponse> {
        // Simulate network delay
        delay(600)

        val result = StaticUserData.loginUser(request.username, request.password)

        return if (result.success) {
            DomainResult.Success(
                LoginResponse(
                    token = result.token,
                    userId = result.userId,
                    message = result.message
                )
            )
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.Unknown(
                    message = result.message ?: "Login failed"
                )
            )
        }
    }

    override suspend fun registerNewUser(request: RegisterRequest): DomainResult<String> {
        // Simulate network delay
        delay(500)

        val result = StaticUserData.registerUser(request.username, request.password)

        return if (result.success) {
            DomainResult.Success(result.message)
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.Unknown(
                    message = result.message
                )
            )
        }
    }

    override suspend fun validateToken(request: ValidateTokenRequest): DomainResult<String> {
        // Simulate network delay
        delay(300)

        val isValid = StaticUserData.validateToken(request.token)

        return if (isValid) {
            DomainResult.Success("Token is valid")
        } else {
            DomainResult.Failure(
                com.agah.furkan.core.domain.model.DomainError.Unknown(
                    message = "Invalid token"
                )
            )
        }
    }
}

