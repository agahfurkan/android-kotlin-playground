package com.agah.furkan.androidplayground.domain.model.request

sealed class UseCaseParams {
    object None : UseCaseParams()
    data class UserLoginParams(val password: String, val username: String) : UseCaseParams()
    data class UserRegisterParams(val password: String, val username: String) : UseCaseParams()
    data class ValidateTokenParams(val token: String) : UseCaseParams()
}
