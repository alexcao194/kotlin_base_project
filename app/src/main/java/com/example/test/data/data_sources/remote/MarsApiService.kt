package com.example.test.data.data_sources.remote

import com.example.test.data.models.HobbyModel
import retrofit2.http.GET

interface HobbiesApiService {
    @GET("hobbies")
    suspend fun getHobbies(): List<HobbyModel>
}