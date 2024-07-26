package com.example.test.configs.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

const val BASE_URL = "https://trapper-server.onrender.com"

private val json = Json {
    ignoreUnknownKeys = true
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
        json.asConverterFactory("application/json".toMediaType())
    )
    .build()