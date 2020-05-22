package ru.somarov.marathon.backend.main.plugin.runner_card.repository

import android.app.Application
import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.plugin.runner_card.dao.RunnerDao
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner

class RunnerCardRepo(application: Application) {
    private val runnerDao: RunnerDao
    private val runners: LiveData<List<Runner>>

    init {
        val db = MarathonDatabase.getDatabase(application)
        runnerDao = db.runnerDao
        runners = runnerDao.getRunners()
    }
}