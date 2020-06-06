package ru.somarov.marathon.backend.main.plugin.login

import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.dao.CountryDao
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.Result
import java.util.*

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(
    val dataSource: RemoteDataSource,
    private val runnerDao: RunnerDao,
    val genderDao: GenderDao,
    val countryDao: CountryDao
) {

    suspend fun logout(id: Int) {
        dataSource.logout(id)
    }

    suspend fun login(username: String, password: String): LiveData<Runner> {
        // handle login
        return runnerDao.getRunner(username, password)
    }


}
