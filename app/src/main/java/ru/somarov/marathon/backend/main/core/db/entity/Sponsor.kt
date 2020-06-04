package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.*

@Entity(tableName = "sponsor",
    foreignKeys = [
        ForeignKey(entity = Runner::class,
            parentColumns = ["id"],
            childColumns = ["id_runner"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index("id_runner")])
data class Sponsor (
    var name: String,
    var id_type: String,
    var amount: Int,
    var id_runner: Int
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null
}