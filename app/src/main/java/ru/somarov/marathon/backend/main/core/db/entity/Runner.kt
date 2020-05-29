package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "runner",
        foreignKeys = [
            ForeignKey(entity = Gender::class,
                parentColumns = ["id"],
                childColumns = ["id_gender"],
                onDelete = CASCADE)],
        indices = [Index("id_gender")])
data class Runner(
    var email: String,
    var id_gender: String,
    @ColumnInfo(name = "dateOfBirth")
    var birthday: String,
    var password: String,
    var countryCode: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}