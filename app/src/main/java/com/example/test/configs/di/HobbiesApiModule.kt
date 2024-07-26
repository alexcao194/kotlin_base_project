package com.example.test.configs.di

import com.example.test.configs.retrofit.retrofit
import com.example.test.data.data_sources.remote.HobbiesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HobbiesApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = retrofit

    @Singleton
    @Provides
    fun provideHobbiesApiService(retrofit: Retrofit): HobbiesApiService {
        return retrofit.create(HobbiesApiService::class.java)
    }
}