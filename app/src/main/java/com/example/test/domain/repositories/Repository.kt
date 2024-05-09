package com.example.test.domain.repositories

import com.example.test.domain.entities.Data
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getDataStream(): Flow<List<Data>>

    suspend fun insertData(data: Data)

    suspend fun deleteData(data: Data)

    suspend fun updateData(data: Data)
}