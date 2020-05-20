package ru.somarov.marathon.backend.main.plugin.runner_card.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Gender(
    @ColumnInfo(name = "gender")
    var id: String
) {
}