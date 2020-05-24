package ru.somarov.marathon.backend.main.plugin.runner_card.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.dao.BaseDao
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Gender
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner

@Dao
interface GenderDao: BaseDao<Gender> {
    @Query("SELECT * from Gender where id = :name")
    suspend fun getGender(name: String): Gender
}