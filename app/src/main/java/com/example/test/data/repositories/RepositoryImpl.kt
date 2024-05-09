package com.example.test.data.repositories

import com.example.test.data.data_sources.database.DataDao
import com.example.test.data.models.toDataModel
import com.example.test.data.models.toEntity
import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataDao: DataDao
) : Repository {
    override fun getDataStream(): Flow<List<Data>> {
        return dataDao.getData().map {
            it.map { dataModel -> dataModel.toEntity() }
        }
    }

    override suspend fun insertData(data: Data) {
        dataDao.insertData(data.toDataModel())
    }

    override suspend fun deleteData(data: Data) {
        dataDao.deleteData(data.toDataModel())
    }

    override suspend fun updateData(data: Data) {
        dataDao.updateData(data.toDataModel())
    }

}