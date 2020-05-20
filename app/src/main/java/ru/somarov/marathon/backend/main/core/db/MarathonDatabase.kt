package ru.somarov.marathon.backend.main.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.somarov.marathon.backend.main.plugin.runner_card.dao.RunnerDao
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner

@Database(entities = [Runner::class], version = 1)
abstract class MarathonDatabase: RoomDatabase() {
    abstract val runnerDao: RunnerDao
}