package com.agah.furkan.androidplayground.domain

sealed class Error(val errorMessage: String) {
    data class CommonError(val message: String) : Error(message)
    data class NetworkError(val message: String) : Error(message)
    object NullResponseError : Error("Null Response")

    companion object {
        const val defaultMessage: String = "Something went wrong!"
    }
}
