package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender

@Dao
interface GenderDao: BaseDao<Gender> {
    @Query("SELECT * from Gender where id = :name")
    suspend fun getGender(name: String): Gender

    @Query("SELECT * from Gender")
    suspend fun getGenders(): LiveData<List<Gender>>
}