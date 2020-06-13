package ru.somarov.marathon.backend.main.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.somarov.marathon.backend.main.core.db.dao.BaseDao
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Subscription

@Dao
interface SubscriptionDao: BaseDao<Subscription> {
    @Query("SELECT * from Subscription where id_runner = :id_runner")
    suspend fun getSubscription(id_runner: Int): Subscription
    @Query("SELECT * from Subscription where id_runner = :id_runner and id_marathon = :id_marathon")
    suspend fun getSubscription(id_runner: Int, id_marathon: Int): Subscription?
    @Query("INSERT INTO Subscription(id_runner, id_marathon, date) VALUES (:id_runner, :id_marathon, '2020-13-06')")
    suspend fun signupToMarathon(id_runner: Int, id_marathon: Int)
}