package com.agah.furkan.androidplayground.util

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        check(returnType is ParameterizedType) {
            "return type must be parameterized"
        }
        val callType = getParameterUpperBound(0, returnType)//call wrapper
        val apiResponseType =
            getParameterUpperBound(0, callType as ParameterizedType)//api response wrapper
        if (getRawType(callType) != ApiResponse::class.java) {
            return null
        }
        return CustomCallAdapter<Any>(apiResponseType)
    }
}