package com.example.test.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.entities.Data

@Entity (tableName = "Data")
data class DataModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = ""
)

fun DataModel.toEntity() = Data(
    id = id,
    name = name
)

fun Data.toDataModel() = DataModel(
    id = id,
    name = name
)