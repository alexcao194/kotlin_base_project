package com.example.test.data.data_sources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.models.DataModel

@Database(entities = [DataModel::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}