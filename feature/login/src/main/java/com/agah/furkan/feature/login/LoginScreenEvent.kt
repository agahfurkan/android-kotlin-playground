package com.agah.furkan.feature.login

internal sealed class LoginScreenEvent {
    object NavigateToRegisterScreen : LoginScreenEvent()
}