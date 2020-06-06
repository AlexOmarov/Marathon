package ru.somarov.marathon.backend.main.plugin.registration

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import ru.somarov.marathon.backend.main.core.db.dao.CountryDao
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.Result
import java.util.*

class RegistrationRepository (
    private val runnerDao: RunnerDao,
    private val genderDao: GenderDao,
    private val countryDao: CountryDao
) {
    suspend fun register(
        email: String,
        name: String,
        id_gender: String,
        birthday: String,
        password: String,
        age: Int,
        id_country: Int,
        token: String?
    ): LiveData<Runner> {
        return runnerDao.getRunner(runnerDao.insert(Runner(
            email,
            name,
            id_gender,
            birthday,
            password,
            age,
            id_country,
            token
        )).toInt())
    }

    suspend fun getCountries(): LiveData<List<Country>> {
        return countryDao.getCountries()
    }

    suspend fun getGenders(): LiveData<List<Gender>> {
        return genderDao.getGenders()
    }
}
