package com.agah.furkan.core.validation

import java.lang.reflect.Field

object ValidatorUtil {

    fun validate(value: Any): List<ValidationResult> {
        val results = mutableListOf<ValidationResult>()
        value.javaClass.declaredFields.toList()
            .filter { field ->
                val validatorClass = getValidatorClass(field)
                field.isAnnotationPresent(Validate::class.java) && validatorClass != null && Validator::class.java.isAssignableFrom(
                    validatorClass
                )
            }
            .forEach { field: Field ->
                val validatorClass = getValidatorClass(field)!!

                field.isAccessible = true
                val fieldValue = field.get(value)

                results += validateField(
                    field,
                    fieldValue,
                    validatorClass
                )
            }
        return results
    }

    private fun getValidatorClass(field: Field): Class<out Any>? {
        return field.getAnnotation(Validate::class.java)?.validator?.java
    }

    private fun validateField(
        field: Field,
        value: Any?,
        annotation: Class<out Any>
    ): ValidationResult {
        val validator =
            annotation.getDeclaredConstructor().newInstance() as Validator<Any>
        val result = validator.validate(field, value)

        return if (result) {
            ValidationResult.Valid
        } else {
            val errorMessage = validator.errorMessage
            if (errorMessage.isNullOrEmpty()) {
                throw IllegalArgumentException("Error message cannot be null or empty")
            }
            ValidationResult.Invalid(errorMessage)
        }
    }
}