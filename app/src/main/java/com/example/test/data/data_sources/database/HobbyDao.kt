package com.example.test.data.data_sources.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.test.data.models.HobbyModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HobbyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHobby(hobby: HobbyModel)

    @Update
    suspend fun updateHobby(hobby: HobbyModel)

    @Delete
    suspend fun deleteHobby(hobby: HobbyModel)

    @Query("SELECT * FROM Hobbies ORDER BY name ASC")
    fun getHobbies(): Flow<List<HobbyModel>>

    @Query("SELECT * FROM Hobbies WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    suspend fun searchHobbies(query: String): List<HobbyModel>
}