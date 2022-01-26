package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.model.result.LoginResult

interface UserRepository {
    suspend fun loginUser(userLoginParams: UseCaseParams.UserLoginParams): Result<LoginResult>
    suspend fun registerNewUser(userRegisterParams: UseCaseParams.UserRegisterParams): Result<String>
    suspend fun validateToken(validateTokenParams: UseCaseParams.ValidateTokenParams): Result<String>
}
