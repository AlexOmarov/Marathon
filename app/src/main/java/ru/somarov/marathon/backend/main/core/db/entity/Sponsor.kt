package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.*

@Entity(tableName = "sponsor",
    foreignKeys = [
        ForeignKey(entity = Runner::class,
            parentColumns = ["id"],
            childColumns = ["id_runner"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Country::class,
            parentColumns = ["id"],
            childColumns = ["id_country"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Gender::class,
            parentColumns = ["id"],
            childColumns = ["id_gender"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [Index("id_runner"), Index("id_country"), Index("id_gender")])
data class Sponsor (
    var name: String,
    var id_type: String,
    var id_gender: String,
    var id_country: Int,
    var amount: Int,
    var id_runner: Int
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null
}