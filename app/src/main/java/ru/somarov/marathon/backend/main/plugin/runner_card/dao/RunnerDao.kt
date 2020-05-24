package ru.somarov.marathon.backend.main.plugin.runner_card.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.dao.BaseDao
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Gender
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner

@Dao
interface RunnerDao: BaseDao<Runner> {

    @Query("SELECT id_gender as id from runner where id = :id")
    fun getRunnerGender(id: Int): Gender

    @Query("SELECT * from runner ORDER BY dateOfBirth")
    fun getRunners(): LiveData<List<Runner>>

}