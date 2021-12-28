package com.agah.furkan.androidplayground.domain

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure<T>(val error: Error) : Result<T>()
}
