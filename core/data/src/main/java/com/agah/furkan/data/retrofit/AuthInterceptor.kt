package com.agah.furkan.data.retrofit

import com.agah.furkan.core.session.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        /*if (sessionManager.interceptRequests.get()) {
            return Response.Builder()
                .code(401)
                .message("Unauthorized")
                .protocol(response.protocol)
                .request(response.request)
                .build()
        }*/
        if (response.code == 401) {
            sessionManager.onUnauthorizedResponseReceived()
        }
        return response
    }

    companion object
}