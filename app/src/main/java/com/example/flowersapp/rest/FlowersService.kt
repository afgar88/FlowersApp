package com.example.flowersapp.rest

import com.example.flowersapp.BuildConfig
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar.SECOND
import java.util.concurrent.TimeUnit

object FlowersService {

    private val httpLogginInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(MyFirstInterceptor())
            .addInterceptor(httpLogginInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val retrofitService =
        Retrofit.Builder()
            .baseUrl(FlowersAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory()
            .client(okHttpClient)
            .build()
            .create(FlowersAPI::class.java)
}