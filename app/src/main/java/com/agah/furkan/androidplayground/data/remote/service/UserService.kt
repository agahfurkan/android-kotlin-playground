package com.agah.furkan.androidplayground.data.remote.service

import com.agah.furkan.androidplayground.data.remote.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.remote.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.data.remote.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.data.remote.model.response.UserLoginResponse
import com.agah.furkan.androidplayground.data.remote.model.response.UserRegisterResponse
import com.agah.furkan.androidplayground.data.remote.model.response.ValidateTokenResponse
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
