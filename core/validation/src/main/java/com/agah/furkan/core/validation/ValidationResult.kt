package com.agah.furkan.core.validation

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val errorMessage: String) : ValidationResult()
}