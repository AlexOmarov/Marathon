package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Marathon

@Dao
interface MarathonDao: BaseDao<Marathon> {
    @Query("SELECT * from Marathon where id = :name")
    fun getMarathon(name: String): LiveData<Marathon>
    @Query("SELECT * from Marathon")
    fun getMarathons(): LiveData<List<Marathon>>
}