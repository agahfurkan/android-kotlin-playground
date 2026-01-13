package com.agah.furkan.domain.user

import com.agah.furkan.core.domain.model.DomainResult

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String?,
    val userId: Long?,
    val message: String?
)

data class RegisterRequest(
    val username: String,
    val password: String
)

data class ValidateTokenRequest(
    val token: String
)

interface UserRepository {
    suspend fun loginUser(request: LoginRequest): DomainResult<LoginResponse>
    suspend fun registerNewUser(request: RegisterRequest): DomainResult<String>
    suspend fun validateToken(request: ValidateTokenRequest): DomainResult<String>
}

