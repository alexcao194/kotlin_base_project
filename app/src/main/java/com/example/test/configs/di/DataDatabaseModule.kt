package com.example.test.configs.di

import android.content.Context
import androidx.room.Room
import com.example.test.data.data_sources.database.DataDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataDatabaseModule {

    @Singleton
    @Provides
    fun provideDataDatabase(
        @ApplicationContext context: Context
    ): DataDatabase = Room.databaseBuilder(context, DataDatabase::class.java, "data")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDataDao(dataDatabase: DataDatabase) = dataDatabase.dataDao()
}