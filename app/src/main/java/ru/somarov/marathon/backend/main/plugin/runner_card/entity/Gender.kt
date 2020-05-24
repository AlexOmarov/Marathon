package ru.somarov.marathon.backend.main.plugin.runner_card.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gender")
data class Gender(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String
)