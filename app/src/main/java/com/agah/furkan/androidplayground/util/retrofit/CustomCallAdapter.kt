package com.agah.furkan.androidplayground.util.retrofit

import com.agah.furkan.androidplayground.data.web.model.ApiResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class CustomCallAdapter<R : Any>(private val type: Type) : CallAdapter<R, Call<ApiResponse<R>>> {
    lateinit var responseCall: Call<ApiResponse<R>>
    override fun adapt(call: Call<R>): Call<ApiResponse<R>> {
        responseCall = object : Call<ApiResponse<R>> {
            override fun enqueue(callback: Callback<ApiResponse<R>>) {
                call.enqueue(object : Callback<R> {
                    override fun onFailure(call: Call<R>, t: Throwable) {
                        callback.onResponse(
                            responseCall,
                            Response.success(ApiResponse.create(t))
                        )
                    }

                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        callback.onResponse(
                            responseCall,
                            Response.success(ApiResponse.create(response))
                        )
                    }
                })
            }

            override fun isExecuted() = call.isExecuted

            override fun isCanceled() = call.isCanceled

            override fun cancel() = call.cancel()
            override fun clone(): Call<ApiResponse<R>> = responseCall.clone()

            override fun execute(): Response<ApiResponse<R>> {
                throw IllegalStateException("not supported")
            }

            override fun request(): Request = call.request()

            override fun timeout(): Timeout = call.timeout()
        }
        return responseCall
    }

    override fun responseType(): Type = type
}
