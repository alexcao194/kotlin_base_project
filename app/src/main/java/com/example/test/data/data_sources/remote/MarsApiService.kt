package com.example.test.data.data_sources.remote

import com.example.test.data.models.HobbyModel
import retrofit2.http.GET

const val BASE_URL = "https://trapper-server.onrender.com"
interface HobbiesApiService {
    @GET("hobbies")
    suspend fun getHobbies(): List<HobbyModel>
}