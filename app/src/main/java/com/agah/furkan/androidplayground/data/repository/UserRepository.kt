package com.agah.furkan.androidplayground.data.repository

import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.data.web.model.request.ValidateTokenBody
import com.agah.furkan.androidplayground.data.web.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun loginUser(userLoginBody: UserLoginBody) = withContext(Dispatchers.IO) {
        userService.loginUser(userLoginBody)
    }

    suspend fun registerNewUser(userRegisterBody: UserRegisterBody) = withContext(Dispatchers.IO) {
        userService.registerNewUser(userRegisterBody)
    }

    suspend fun validateToken(validateTokenBody: ValidateTokenBody) = withContext(Dispatchers.IO) {
        userService.validateToken(validateTokenBody)
    }
}