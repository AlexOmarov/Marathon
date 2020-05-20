package ru.somarov.marathon.backend.main.plugin.runner_card.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runner")
data class Runner(
    var email: String,
    var gender: Gender,
    @ColumnInfo(name = "dateOfBirth")
    var birthday: String,
    var countryCode: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}