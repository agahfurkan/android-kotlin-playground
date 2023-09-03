package com.agah.furkan.core.validation

import java.lang.reflect.Field

interface Validator<T> {
    var errorMessage: String?

    fun validate(field: Field, value: T?): Boolean
}