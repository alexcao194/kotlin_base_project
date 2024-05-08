package com.example.test.configs.di

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
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }
}