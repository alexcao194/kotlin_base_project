package com.example.test.data.data_sources.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.test.data.models.DataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: DataModel)

    @Update
    suspend fun updateData(data: DataModel)

    @Delete
    suspend fun deleteData(data: DataModel)

    @Query("SELECT * FROM Data ORDER BY name ASC")
    fun getData(): Flow<List<DataModel>>

    @Query("SELECT * FROM Data WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    suspend fun searchData(query: String): List<DataModel>
}