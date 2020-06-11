package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Marathon
import ru.somarov.marathon.backend.main.core.db.entity.Runner

@Dao
interface MarathonDao: BaseDao<Marathon> {
    @Query("SELECT * from Marathon where name = :name")
    fun getMarathon(name: String): LiveData<Marathon>
    @Query("SELECT * from Marathon where id = :id")
    fun getMarathon(id: Int): LiveData<Marathon>
    @Query("SELECT * from Marathon")
    fun getMarathons(): LiveData<List<Marathon>>
    @Query("SELECT Runner.* from Runner inner join subscription on subscription.id_runner = runner.id inner join marathon on marathon.id = subscription.id_marathon where marathon.id = :id")
    fun getRunnersByMarathon(id: Int): LiveData<List<Runner>>
}