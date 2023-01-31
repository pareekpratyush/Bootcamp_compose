package com.example.bootcampcompose.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://run.mocky.io/v3/b6a30bb0-140f-4966-8608-1dc35fa1fadc/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService{
    @GET(".")
    suspend fun getItems():ApiItem
}

object Api{
    val retrofitService:ApiService by lazy{retrofit.create(ApiService::class.java)}
}