package com.agah.furkan.androidplayground.data.web.service

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.data.web.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.data.web.model.response.UserLoginResponse
import com.agah.furkan.androidplayground.data.web.model.response.UserRegisterResponse
import com.agah.furkan.androidplayground.data.web.model.response.ValidateTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("Login/Login")
    suspend fun loginUser(
        @Body userLoginBody: UserLoginBody
    ): ApiResponse<UserLoginResponse>

    @POST("Login/Register")
    suspend fun registerNewUser(
        @Body userRegisterBody: UserRegisterBody
    ): ApiResponse<UserRegisterResponse>

    @POST("Login/ValidateToken")
    suspend fun validateToken(
        @Body validateTokenBody: ValidateTokenBody
    ): ApiResponse<ValidateTokenResponse>
}
