package com.example.homework5

import com.example.homework5.model.GetSearchDataResource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestRetrofit {

    private const val BASE_URL = "https://dapi.kakao.com"

    //받는 reponse를 로그 찍어볼 수 있어서 추가, 오류 시 어떤 것이 문제 인지도 출력해줘서 넣었습니다
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(TestInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val search : GetSearchDataResource by lazy {
        retrofit.create(GetSearchDataResource::class.java)
    }
}