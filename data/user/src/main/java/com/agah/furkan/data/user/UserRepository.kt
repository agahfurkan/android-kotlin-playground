package com.agah.furkan.data.user

import com.agah.furkan.core.data.model.Result
import com.agah.furkan.data.user.remote.model.request.UserLoginBody
import com.agah.furkan.data.user.remote.model.request.UserRegisterBody
import com.agah.furkan.data.user.remote.model.request.ValidateTokenBody
import com.agah.furkan.data.user.remote.model.response.UserLoginResponse

interface UserRepository {
    suspend fun loginUser(userLoginBody: UserLoginBody): Result<UserLoginResponse>
    suspend fun registerNewUser(userRegisterBody: UserRegisterBody): Result<String>
    suspend fun validateToken(validateTokenBody: ValidateTokenBody): Result<String>
}
