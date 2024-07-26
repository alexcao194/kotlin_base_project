package com.alexcao.baseproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexcao.baseproject.domain.entities.Hobby
import kotlinx.serialization.Serializable

@Entity (tableName = "Hobbies")
@Serializable
data class HobbyModel (
    @PrimaryKey
    val id: String = "",
    val name: String = ""
)

fun HobbyModel.toEntity() = Hobby(
    id = id,
    name = name
)

fun Hobby.toHobbyModel() = HobbyModel(
    id = id,
    name = name
)