package com.agah.furkan.domain.user

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.user.UserRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<String> {
        return userRepository.registerNewUser(
            com.agah.furkan.data.user.remote.model.request.UserRegisterBody(
                username = username,
                password = password
            )
        )
    }
}

