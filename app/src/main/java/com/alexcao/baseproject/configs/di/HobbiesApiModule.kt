package com.alexcao.baseproject.configs.di

import com.alexcao.baseproject.configs.retrofit.retrofit
import com.alexcao.baseproject.data.data_sources.remote.HobbiesApiService
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