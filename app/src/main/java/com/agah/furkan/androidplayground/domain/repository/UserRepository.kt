package com.agah.furkan.androidplayground.domain.repository

import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.domain.model.request.UserLoginParams
import com.agah.furkan.androidplayground.domain.model.request.UserRegisterParams
import com.agah.furkan.androidplayground.domain.model.request.ValidateTokenParams
import com.agah.furkan.androidplayground.domain.model.result.LoginResult

interface UserRepository {
    suspend fun loginUser(userLoginParams: UserLoginParams): Result<LoginResult>
    suspend fun registerNewUser(userRegisterParams: UserRegisterParams): Result<String>
    suspend fun validateToken(validateTokenParams: ValidateTokenParams): Result<String>
}
