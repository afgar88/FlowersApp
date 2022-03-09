package com.example.flowersapp.rest

import com.example.flowersapp.model.Flowers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface FlowersAPI {

    //https://services.hanselandpetal.com/feeds/flowers.json
    @GET("feeds/flowers.json")
    fun getFlowers(): Call<Flowers>

    companion object {
        const val BASE_URL = "https://services.hanselandpetal.com/"
        const val PHOTOS_URL= BASE_URL+"photos/"


    }
}