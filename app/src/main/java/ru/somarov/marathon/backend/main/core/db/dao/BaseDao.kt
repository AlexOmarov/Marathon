package ru.somarov.marathon.backend.main.core.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T): Long

    @Update
    suspend fun update(t: T)

    @Delete
    suspend fun delete(t: T)

}