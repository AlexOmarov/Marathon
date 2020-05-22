package ru.somarov.marathon.backend.main.core.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Long

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)
}