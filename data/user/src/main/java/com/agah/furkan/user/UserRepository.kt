package com.agah.furkan.user

import com.agah.furkan.user.remote.model.request.UserLoginBody
import com.agah.furkan.user.remote.model.request.UserRegisterBody
import com.agah.furkan.user.remote.model.request.ValidateTokenBody
import com.agah.furkan.user.remote.model.response.UserLoginResponse

interface UserRepository {
    suspend fun loginUser(userLoginBody: UserLoginBody): com.agah.furkan.data.model.Result<UserLoginResponse>
    suspend fun registerNewUser(userRegisterBody: UserRegisterBody): com.agah.furkan.data.model.Result<String>
    suspend fun validateToken(validateTokenBody: ValidateTokenBody): com.agah.furkan.data.model.Result<String>
}
