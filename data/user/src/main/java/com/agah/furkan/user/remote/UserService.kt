package com.agah.furkan.user.remote

import com.agah.furkan.user.remote.model.request.UserLoginBody
import com.agah.furkan.user.remote.model.request.UserRegisterBody
import com.agah.furkan.user.remote.model.request.ValidateTokenBody
import com.agah.furkan.user.remote.model.response.UserLoginResponse
import com.agah.furkan.user.remote.model.response.UserRegisterResponse
import com.agah.furkan.user.remote.model.response.ValidateTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("Login/Login")
    suspend fun loginUser(
        @Body userLoginBody: UserLoginBody
    ): UserLoginResponse

    @POST("Login/Register")
    suspend fun registerNewUser(
        @Body userRegisterBody: UserRegisterBody
    ): UserRegisterResponse

    @POST("Login/ValidateToken")
    suspend fun validateToken(
        @Body validateTokenBody: ValidateTokenBody
    ): ValidateTokenResponse
}
