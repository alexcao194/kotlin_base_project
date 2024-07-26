package com.alexcao.baseproject.configs.di

import com.alexcao.baseproject.data.data_sources.database.HobbyDao
import com.alexcao.baseproject.data.data_sources.remote.HobbiesApiService
import com.alexcao.baseproject.data.repositories.RepositoryImpl
import com.alexcao.baseproject.domain.repositories.Repository
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