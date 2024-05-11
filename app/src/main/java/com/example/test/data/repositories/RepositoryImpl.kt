package com.example.test.data.repositories

import com.example.test.data.data_sources.database.HobbyDao
import com.example.test.data.data_sources.remote.HobbiesApiService
import com.example.test.data.models.toHobbyModel
import com.example.test.data.models.toEntity
import com.example.test.domain.entities.Hobby
import com.example.test.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val hobbyDao: HobbyDao,
    private val hobbiesApiService: HobbiesApiService
) : Repository {
    override fun getHobbyStream(): Flow<List<Hobby>> {
        return hobbyDao.getHobbies().map {
            it.map { dataModel -> dataModel.toEntity() }
        }
    }

    override suspend fun getHobbies() {
        val hobbies = hobbiesApiService.getHobbies()
        for (hobby in hobbies) {
            hobbyDao.insertHobby(hobby)
        }
    }

    override suspend fun insertHobby(hobby: Hobby) {
        hobbyDao.insertHobby(hobby.toHobbyModel())
    }

    override suspend fun deleteHobby(hobby: Hobby) {
        hobbyDao.deleteHobby(hobby.toHobbyModel())
    }

    override suspend fun updateHobby(hobby: Hobby) {
        hobbyDao.updateHobby(hobby.toHobbyModel())
    }

}