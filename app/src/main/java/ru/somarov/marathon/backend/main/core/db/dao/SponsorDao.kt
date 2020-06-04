package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Marathon
import ru.somarov.marathon.backend.main.core.db.entity.Sponsor

@Dao
interface SponsorDao: BaseDao<Sponsor> {
    @Query("SELECT * from Sponsor where name = :name")
    fun getSponsor(name: String): LiveData<Sponsor>
    @Query("SELECT * from Sponsor where id = :id")
    fun getSponsor(id: Int): LiveData<Sponsor>
    @Query("SELECT * from Sponsor")
    fun getSponsors(): LiveData<List<Sponsor>>
}