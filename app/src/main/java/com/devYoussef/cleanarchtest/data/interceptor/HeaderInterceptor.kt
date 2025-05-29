package com.devYoussef.cleanarchtest.data.interceptor

import com.devYoussef.cleanarchtest.common.constants.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.newBuilder().addHeader(
            "Authorization", "Bearer ${Constants.API_KEY}"
        )

        return chain.proceed(url.build())
    }
}