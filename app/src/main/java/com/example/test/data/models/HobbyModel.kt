package com.example.test.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.entities.Hobby

@Entity (tableName = "Hobbies")
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