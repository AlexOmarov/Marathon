package ru.somarov.marathon.backend.main.plugin.login

import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.Result
import ru.somarov.marathon.backend.main.plugin.login.dto.LoggedInUser
import java.util.*

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: RemoteDataSource, private val runnerDao: RunnerDao, val genderDao: GenderDao) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    suspend fun logout(id: Int) {
        user = null
        dataSource.logout(id)
    }

    suspend fun login(username: String, password: String): Runner? {
        // handle login
        val result = dataSource.login(username, password)
        var runner: Runner? = null

        if (result is Result.Success) {
            val dto = result.data
            runner = runnerDao.getRunner(runnerDao.insert(Runner(birthday = dto.birthday, countryCode = dto.countryCode,
            email = dto.email, id_gender = dto.gender, token = UUID.randomUUID().toString()).also { it.id = dto.id }).toInt()).value
            val loggedUser = runner?.email?.let { LoggedInUser(runner.id.toString(), it) }
            if (loggedUser != null) {
                setLoggedInUser(loggedUser)
            }
        }
        return runner
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
