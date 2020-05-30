package ru.somarov.marathon.backend.main.core.api.entity

import androidx.room.ColumnInfo
import java.util.*

data class Runner(
    var email: String,
    var gender: String,
    var birthday: String,
    var id: Int,
    var countryCode: String
)