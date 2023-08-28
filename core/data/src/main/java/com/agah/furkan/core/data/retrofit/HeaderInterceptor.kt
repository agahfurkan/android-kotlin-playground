package com.agah.furkan.core.data.retrofit

import com.agah.furkan.core.preferences.UserPreference
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val userPreference: UserPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        userPreference.getToken()?.let { token ->
            requestBuilder.header("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}
