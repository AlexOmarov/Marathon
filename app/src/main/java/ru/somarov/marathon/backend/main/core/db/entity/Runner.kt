package ru.somarov.marathon.backend.main.core.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*

@Entity(tableName = "runner",
        foreignKeys = [
            ForeignKey(entity = Gender::class,
                parentColumns = ["id"],
                childColumns = ["id_gender"],
                onDelete = CASCADE),
            ForeignKey(entity = Country::class,
                parentColumns = ["id"],
                childColumns = ["id_country"],
                onDelete = CASCADE)],
        indices = [Index("id_gender"), Index("id_country")])
data class Runner (
    var email: String,
    var name: String,
    var id_gender: String,
    @ColumnInfo(name = "dateOfBirth")
    var birthday: String,
    var password: String,
    var age: Int,
    var id_country: Int,
    var token: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}