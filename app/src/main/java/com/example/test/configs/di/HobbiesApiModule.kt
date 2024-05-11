package com.example.test.configs.di

import com.example.test.data.data_sources.remote.BASE_URL
import com.example.test.data.data_sources.remote.HobbiesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HobbiesApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideHobbiesApiService(retrofit: Retrofit): HobbiesApiService {
        return retrofit.create(HobbiesApiService::class.java)
    }
}