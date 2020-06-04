package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner

@Dao
interface RunnerDao: BaseDao<Runner> {

    @Query("SELECT id_gender as id from runner where id = :id")
    fun getRunnerGender(id: Int): Gender

    @Query("SELECT * from runner ORDER BY dateOfBirth")
    fun getRunners(): LiveData<List<Runner>>

    @Query("SELECT * from runner WHERE name = :name")
    fun getRunner(name: String): LiveData<Runner>

    @Query("SELECT * from runner where id = :id")
    fun getRunner(id: Int): LiveData<Runner>

    @Query("UPDATE Runner set token = null")
    fun logout()

}