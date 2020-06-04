package ru.somarov.marathon.backend.main.core.db.dao

import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Subscription

@Dao
interface SubscriptionDao: BaseDao<Subscription> {
    @Query("SELECT * from Subscription where id_runner = :id_runner")
    suspend fun getGender(id_runner: Int): Subscription
}