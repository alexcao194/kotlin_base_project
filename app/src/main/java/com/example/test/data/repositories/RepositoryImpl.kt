package com.example.test.data.repositories

import com.example.test.domain.entities.Data
import com.example.test.domain.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RepositoryImpl : Repository {
    override suspend fun getDataSuspending(): Data {
        // await 2 seconds
        delay(2000)
        val result = Data(id = 2, name = "Suspending")
        return result
    }

    override fun getData(): Data {
        return Data(id = 1, name = "Regular")
    }
}