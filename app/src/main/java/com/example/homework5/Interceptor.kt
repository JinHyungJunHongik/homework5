package com.example.homework5

import okhttp3.Interceptor
import okhttp3.Response

class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format(
                    "c2e35e66f144e5d544ec6782c56b342b"
                )
            ).build()

        return chain.proceed(request)
    }
}