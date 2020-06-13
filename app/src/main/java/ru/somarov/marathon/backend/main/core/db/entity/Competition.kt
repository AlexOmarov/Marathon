package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competition")
data class Competition(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String,
    var id_type: String
)