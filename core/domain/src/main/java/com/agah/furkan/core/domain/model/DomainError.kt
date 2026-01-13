package com.agah.furkan.core.domain.model

/**
 * Represents domain-level errors that can occur during business operations.
 * This is a pure domain concept with no knowledge of data layer specifics.
 */
sealed class DomainError(open val message: String) {

    /** Generic error for unexpected situations */
    data class Unknown(override val message: String = DEFAULT_MESSAGE) : DomainError(message)

    /** Error when a requested resource is not found */
    data class NotFound(override val message: String = "Resource not found") : DomainError(message)

    /** Error when user is not authorized */
    data class Unauthorized(override val message: String = "Unauthorized access") : DomainError(message)

    /** Error when there's a network connectivity issue */
    data class Network(override val message: String = "Network error occurred") : DomainError(message)

    /** Error when server returns an error */
    data class Server(override val message: String = "Server error occurred") : DomainError(message)

    /** Error when response data is invalid or null */
    data class InvalidData(override val message: String = "Invalid data received") : DomainError(message)

    /** Error for validation failures */
    data class Validation(override val message: String = "Validation failed") : DomainError(message)

    companion object {
        const val DEFAULT_MESSAGE: String = "Something went wrong!"
    }
}

