package ru.somarov.marathon.backend.main.plugin.runner_list

import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource


class ListRepo(private val runnerDao: RunnerDao, private val genderDao: GenderDao, private val remoteSource: RemoteDataSource) {

    // Marathon in remote
    suspend fun getMarathons() = remoteSource.getRemoteMarathons()
    suspend fun getFreeMarathons(id: Int) = remoteSource.getFreeMarathons(id)

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

    suspend fun getRunner(name: String): LiveData<Runner> {
        return runnerDao.getRunner(name)
    }
    suspend fun logout() {
        return runnerDao.logout()
    }
}