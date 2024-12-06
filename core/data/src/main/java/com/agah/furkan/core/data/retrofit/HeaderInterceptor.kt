package com.agah.furkan.core.data.retrofit

import com.agah.furkan.core.preferences.UserPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val userPreference: UserPreference,
    private val coroutineScope: CoroutineScope
) : Interceptor {

    private var token: String? = null

    init {
        coroutineScope.launch {
            token = userPreference.getToken()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (token.isNullOrEmpty()) {
            token = userPreference.getTokenSync()
        }
        requestBuilder.header("Authorization", "Bearer $token")
        return chain.proceed(requestBuilder.build())
    }
}
