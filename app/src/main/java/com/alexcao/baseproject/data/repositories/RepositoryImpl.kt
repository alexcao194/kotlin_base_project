package com.alexcao.baseproject.data.repositories

import com.alexcao.baseproject.data.data_sources.database.HobbyDao
import com.alexcao.baseproject.data.data_sources.remote.HobbiesApiService
import com.alexcao.baseproject.data.models.toHobbyModel
import com.alexcao.baseproject.data.models.toEntity
import com.alexcao.baseproject.domain.entities.Hobby
import com.alexcao.baseproject.domain.repositories.Repository
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