package com.agah.furkan.androidplayground.ui.login

sealed class LoginScreenEvent {
    object NavigateToRegisterScreen : LoginScreenEvent()
}