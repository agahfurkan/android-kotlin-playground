package com.agah.furkan.feature.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agah.furkan.core.domain.model.DomainResult
import com.agah.furkan.domain.user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RegisterScreenVM @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) :
    ViewModel() {

    private val _registerUserResponse = Channel<DomainResult<String>>(Channel.BUFFERED)
    val registerUserResponse = _registerUserResponse.receiveAsFlow()

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun registerNewUser() {
        viewModelScope.launch {
            val response = registerUserUseCase(
                username = username,
                password = password
            )
            _registerUserResponse.send(response)
        }
    }
}
