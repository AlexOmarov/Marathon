package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.*

@Entity(tableName = "marathon")
data class Marathon (
    var name: String,
    var address: String,
    var id_country: Int,
    var type: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null
}