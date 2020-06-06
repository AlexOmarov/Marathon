package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender

@Dao
interface CountryDao: BaseDao<Country> {
    @Query("SELECT * from country where name = :name")
    suspend fun getCountry(name: String): Country
    @Query("SELECT * from country")
    suspend fun getCountries(): LiveData<List<Country>>

}