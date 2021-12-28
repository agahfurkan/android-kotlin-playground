package com.agah.furkan.androidplayground.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _errorEvent = MutableSharedFlow<Error>()
    val errorEvent: SharedFlow<Error> get() = _errorEvent

    fun handleError(error: Error) {
        viewModelScope.launch {
            _errorEvent.emit(error)
        }
    }
}
