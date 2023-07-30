package com.agah.furkan.login

internal sealed class LoginScreenEvent {
    object NavigateToRegisterScreen : LoginScreenEvent()
}