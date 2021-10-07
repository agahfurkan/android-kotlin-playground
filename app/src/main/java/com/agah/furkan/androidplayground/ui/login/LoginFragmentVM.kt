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

    fun login(userLoginBody: UserLoginBody) {
        viewModelScope.launch {
            val response = userRepository.loginUser(userLoginBody)
            _loginResponse.emit(response)
        }
    }
}
