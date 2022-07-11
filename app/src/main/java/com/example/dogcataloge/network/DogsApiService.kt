package com.example.dogcataloge.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiService {
    @GET("breeds/list/all")
    suspend fun getListDogs() : String

    @GET("breed/{breed}/images/random")
    suspend fun getImages(@Path("breed") breed: String) : String
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://dog.ceo/api/")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

var service : DogsApiService = retrofit.create<DogsApiService>(DogsApiService::class.java)