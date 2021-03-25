package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.LoginRepository
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentVM @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    fun login(userLoginBody: UserLoginBody) {
        viewModelScope.launch {
            loginRepository.loginUser(userLoginBody)
        }
    }

    fun registerNewUser(userRegisterBody: UserRegisterBody) {
        viewModelScope.launch {
            loginRepository.registerNewUser(userRegisterBody)
        }
    }
}