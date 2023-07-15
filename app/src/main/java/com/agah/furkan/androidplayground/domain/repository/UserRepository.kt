package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.data.model.Result
import com.agah.furkan.androidplayground.domain.model.request.UseCaseParams
import com.agah.furkan.androidplayground.domain.model.result.LoginResult

interface UserRepository {
    suspend fun loginUser(userLoginParams: UseCaseParams.UserLoginParams): com.agah.furkan.data.model.Result<LoginResult>
    suspend fun registerNewUser(userRegisterParams: UseCaseParams.UserRegisterParams): com.agah.furkan.data.model.Result<String>
    suspend fun validateToken(validateTokenParams: UseCaseParams.ValidateTokenParams): com.agah.furkan.data.model.Result<String>
}
