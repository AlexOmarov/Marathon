package ru.somarov.marathon.backend.main.plugin.runner_card.repository

import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource


class RunnerCardRepo(private val runnerDao: RunnerDao, private val genderDao: GenderDao, private val remoteSource: RemoteDataSource) {
    private val runners: LiveData<List<Runner>> = runnerDao.getRunners()

    // Marathon in remote
    suspend fun getRunnerMarathons(id: Int) = remoteSource.getRemoteMarathons(id)

    // Runner in database
    suspend fun insertRunner(runner: Runner) {
        runnerDao.insert(runner)
    }

    suspend fun updateRunner(runner: Runner) {
        runnerDao.update(runner)
    }

    suspend fun deleteRunner(runner: Runner) {
        runnerDao.delete(runner)
    }

    suspend fun insertGender(gender: Gender) {
        genderDao.insert(gender)
    }

    suspend fun updateGender(gender: Gender) {
        genderDao.update(gender)
    }

    suspend fun deleteGender(gender: Gender) {
        genderDao.delete(gender)
    }

    fun getAllRunners(): LiveData<List<Runner>> {
        return runnerDao.getRunners()
    }

    suspend fun getGender(name: String): Gender {
        return genderDao.getGender(name)
    }
}