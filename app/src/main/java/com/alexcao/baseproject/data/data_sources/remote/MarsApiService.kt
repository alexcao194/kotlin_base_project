package com.alexcao.baseproject.data.data_sources.remote

import com.alexcao.baseproject.data.models.HobbyModel
import retrofit2.http.GET

interface HobbiesApiService {
    @GET("hobbies")
    suspend fun getHobbies(): List<HobbyModel>
}