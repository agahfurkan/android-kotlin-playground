package com.agah.furkan.core.validation

import kotlin.reflect.KClass

@Retention
@Target(AnnotationTarget.FIELD)
annotation class Validate(
    val validator: KClass<*>,
    val priority: Int = 0
)