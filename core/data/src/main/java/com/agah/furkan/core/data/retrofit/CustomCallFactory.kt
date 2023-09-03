package com.agah.furkan.core.data.retrofit

import com.agah.furkan.core.data.model.ApiResponse
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
        val callType = getParameterUpperBound(0, returnType) // call wrapper
        if (callType !is ParameterizedType || getRawType(callType) != ApiResponse::class.java) {
            return null
        }
        val apiResponseType =
            getParameterUpperBound(0, callType) // api response wrapper

        return CustomCallAdapter<Any>(apiResponseType)
    }
}
