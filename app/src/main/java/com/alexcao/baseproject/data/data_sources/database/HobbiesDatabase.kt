package com.alexcao.baseproject.data.data_sources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexcao.baseproject.data.models.HobbyModel

//const val HOBBIES_DATABASE_NAME = "Hobbies"

@Database(entities = [HobbyModel::class], version = 1, exportSchema = false)
abstract class HobbiesDatabase : RoomDatabase() {
    companion object {
        const val HOBBIES_DATABASE_NAME = "Hobbies"
    }
    abstract fun hobbyDao(): HobbyDao
}