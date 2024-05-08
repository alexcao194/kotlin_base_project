package com.example.test.domain.repositories

import com.example.test.domain.entities.Data

interface Repository {
    suspend fun getDataSuspending(): Data
    fun  getData(): Data
}