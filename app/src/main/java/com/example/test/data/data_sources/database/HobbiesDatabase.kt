package com.example.test.data.data_sources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.data.models.HobbyModel

@Database(entities = [HobbyModel::class], version = 1, exportSchema = false)
abstract class HobbiesDatabase : RoomDatabase() {
    abstract fun hobbyDao(): HobbyDao
}