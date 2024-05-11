package com.example.test.configs.di

import com.example.test.data.data_sources.database.HobbyDao
import com.example.test.data.data_sources.remote.HobbiesApiService
import com.example.test.data.repositories.RepositoryImpl
import com.example.test.domain.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(hobbyDao: HobbyDao, hobbiesApiService: HobbiesApiService): Repository {
        return RepositoryImpl(
            hobbyDao = hobbyDao,
            hobbiesApiService = hobbiesApiService
        )
    }
}