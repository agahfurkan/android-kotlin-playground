package com.agah.furkan.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.user.UserRepository
import com.agah.furkan.user.remote.model.request.UserRegisterBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegisterScreenVM @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _registerUserResponse =
        MutableSharedFlow<com.agah.furkan.data.model.Result<String>>()
    val registerUserResponse = _registerUserResponse.asSharedFlow()

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    fun registerNewUser() {
        viewModelScope.launch {
            val response = userRepository.registerNewUser(
                UserRegisterBody(
                    username = username,
                    password = password
                )
            )
            _registerUserResponse.emit(response)
        }
    }
}
