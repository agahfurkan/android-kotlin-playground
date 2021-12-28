package com.agah.furkan.androidplayground.data.remote

import com.agah.furkan.androidplayground.util.SharedPrefUtil

object RestConstants {
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L

    fun getAuthHeader(): HashMap<String, String> {
        val temp = HashMap<String, String>()
        temp["Authorization"] = "Bearer ${SharedPrefUtil.getToken()}"
        return temp
    }
}
