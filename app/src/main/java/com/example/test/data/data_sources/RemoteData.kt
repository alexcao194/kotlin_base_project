package com.example.test.data.data_sources

interface RemoteData {
    suspend fun getData(): String
}

class RemoteDataImpl : RemoteData {
    override suspend fun getData(): String {
        return "RemoteData"
    }
}