package com.agah.furkan.login

sealed class LoginScreenEvent {
    object NavigateToRegisterScreen : LoginScreenEvent()
}