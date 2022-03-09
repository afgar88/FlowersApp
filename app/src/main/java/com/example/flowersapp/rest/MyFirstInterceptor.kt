package com.example.flowersapp.rest

import okhttp3.Interceptor
import okhttp3.Response

/*this interceptor is for the request to the network
*
* you can add the headers rigth here*/

class MyFirstInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //rigth here you do your work
        val request = chain.request().newBuilder().apply {
            addHeader("HEADER", "FirstHeader")
            addHeader("HEADER2", "SecondHeader")
            addHeader("HEADER2", "ThirdHeader")
        }.build()
        return chain.proceed(request)
    }
}