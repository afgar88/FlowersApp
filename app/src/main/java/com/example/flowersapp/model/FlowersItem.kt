package com.example.flowersapp.model


import com.google.gson.annotations.SerializedName

data class FlowersItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("productId")
    val productId: Int
)