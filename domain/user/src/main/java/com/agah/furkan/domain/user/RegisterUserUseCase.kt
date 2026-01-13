package com.agah.furkan.domain.user

import com.agah.furkan.core.domain.model.DomainResult
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): DomainResult<String> {
        return userRepository.registerNewUser(
            RegisterRequest(
                username = username,
                password = password
            )
        )
    }
}

