package ru.somarov.marathon.backend.main.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.somarov.marathon.backend.main.plugin.runner_card.dao.RunnerDao
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner

@Database(entities = [Runner::class], version = 1)
abstract class MarathonDatabase: RoomDatabase() {
    abstract val runnerDao: RunnerDao


    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, PRIMARY KEY(`id`))")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Fruit ADD COLUMN pub_year INTEGER")
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MarathonDatabase? = null

        fun getDatabase(context: Context): MarathonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarathonDatabase::class.java,
                    "marathon_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}