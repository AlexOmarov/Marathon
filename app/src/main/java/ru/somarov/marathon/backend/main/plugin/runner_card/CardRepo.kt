package ru.somarov.marathon.backend.main.plugin.runner_card

import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.dao.CountryDao
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource


class CardRepo(
    private val runnerDao: RunnerDao,
    private val genderDao: GenderDao,
    private val countryDao: CountryDao,
    private val remoteSource: RemoteDataSource
) {

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

    suspend fun getRunner(id: Int): LiveData<Runner> {
        return runnerDao.getRunner(id)
    }

    suspend fun getGender(name: String): Gender {
        return genderDao.getGender(name)
    }

    suspend fun getCountry(name: String): Country {
        return countryDao.getCountry(name)
    }

    fun getRunners(): LiveData<List<Runner>> {
        return runnerDao.getRunners()
    }

    suspend fun logout() {
        return runnerDao.logout()
    }

    suspend fun insertCountry(country: Country): Long {
        return countryDao.insert(country)
    }
}