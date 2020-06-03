package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,
    var name: String,
    var img: String
)