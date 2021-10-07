package com.agah.furkan.androidplayground.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.androidplayground.data.repository.UserRepository
import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.data.web.model.response.UserLoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginFragmentVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _loginResponse = MutableSharedFlow<ApiResponse<UserLoginResponse>>()
    val loginResponse: SharedFlow<ApiResponse<UserLoginResponse>> get() = _loginResponse
    var username: String? = null
    var password: String? = null

    private fun login(userLoginBody: UserLoginBody) {
        viewModelScope.launch {
            val response = userRepository.loginUser(userLoginBody)
            _loginResponse.emit(response)
        }
    }

    fun onLoginBtnClicked() {
        if (username.isNullOrBlank())
            return
        if (password.isNullOrBlank())
            return
        login(UserLoginBody(username = username!!, password = password!!))
    }
}
