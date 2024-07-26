package com.alexcao.baseproject.domain.repositories

import com.alexcao.baseproject.domain.entities.Hobby
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getHobbyStream(): Flow<List<Hobby>>

    suspend fun getHobbies()

    suspend fun insertHobby(hobby: Hobby)

    suspend fun deleteHobby(hobby: Hobby)

    suspend fun updateHobby(hobby: Hobby)
}