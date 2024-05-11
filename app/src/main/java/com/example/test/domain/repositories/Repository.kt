package com.example.test.domain.repositories

import com.example.test.domain.entities.Hobby
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getHobbyStream(): Flow<List<Hobby>>

    suspend fun getHobbies()

    suspend fun insertHobby(hobby: Hobby)

    suspend fun deleteHobby(hobby: Hobby)

    suspend fun updateHobby(hobby: Hobby)
}