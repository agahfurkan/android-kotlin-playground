package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun login(userLoginBody: UserLoginBody) {
        viewModelScope.launch {
            userRepository.loginUser(userLoginBody)
        }
    }
}