package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "subscription",
    foreignKeys = [
        ForeignKey(entity = Marathon::class,
            parentColumns = ["id"],
            childColumns = ["id_marathon"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Runner::class,
            parentColumns = ["id"],
            childColumns = ["id_runner"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index("id_marathon"), Index("id_runner")])
data class Subscription (
    var id_marathon: Int,
    var id_runner: Int,
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}