package ru.somarov.marathon.backend.main.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import ru.somarov.marathon.backend.main.core.db.dao.CountryDao
import ru.somarov.marathon.backend.main.core.db.dao.GenderDao
import ru.somarov.marathon.backend.main.core.db.dao.RunnerDao
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner

@Database(entities = [Runner::class, Gender::class, Country::class], version = 1, exportSchema = false)
abstract class MarathonDatabase: RoomDatabase() {
    abstract val runnerDao: RunnerDao
    abstract val genderDao: GenderDao
    abstract val countryDao: CountryDao

    companion object {

        @Volatile
        private var INSTANCE: MarathonDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("INSERT INTO Gender values('MALE'), ('FEMALE')")
            }
        }


        fun getDatabase(context: Context): MarathonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context, MarathonDatabase::class.java,
                    "marathon_database")
                    //.setJournalMode(JournalMode.TRUNCATE)
                    //.addMigrations(MIGRATION_1_2)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // insert the data on the IO Thread
                            Thread {
                                //getDatabase(context).runnerDao.insert()
                            }.start()
                        }
                    })
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }



}