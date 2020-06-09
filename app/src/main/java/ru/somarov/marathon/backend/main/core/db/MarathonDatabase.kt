package ru.somarov.marathon.backend.main.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.dao.*
import ru.somarov.marathon.backend.main.core.db.entity.*

@Database(
    entities = [
        Runner::class,
        Gender::class,
        Marathon::class,
        Sponsor::class,
        Subscription::class,
        Country::class
    ], version = 1, exportSchema = false
)
abstract class MarathonDatabase : RoomDatabase() {
    abstract val runnerDao: RunnerDao
    abstract val genderDao: GenderDao
    abstract val countryDao: CountryDao
    abstract val marathonDao: MarathonDao
    abstract val sponsorDao: SponsorDao
    abstract val subscriptionDao: SubscriptionDao

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
                val instance = Room.databaseBuilder(
                    context, MarathonDatabase::class.java,
                    "marathon_database"
                )
                    //.setJournalMode(JournalMode.TRUNCATE)
                    //.addMigrations(MIGRATION_1_2)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // insert the data on the IO Thread
                            GlobalScope.launch {
                                val genderDao = getDatabase(context).genderDao
                                val countryDao = getDatabase(context).countryDao
                                val runnerDao = getDatabase(context).runnerDao
                                val marathonDao = getDatabase(context).marathonDao
                                val sponsorDao = getDatabase(context).sponsorDao
                                val subscriptionDao = getDatabase(context).subscriptionDao

                                genderDao.insert(Gender("MALE"))
                                genderDao.insert(Gender("FEMALE"))

                                val rus = countryDao.insert(
                                    Country(
                                        id = 1,
                                        code = "RUS",
                                        name = "Russia",
                                        img = "russia"
                                    )
                                )
                                val usa = countryDao.insert(
                                    Country(
                                        id = 2,
                                        code = "USA",
                                        name = "Usa",
                                        img = "usa"
                                    )
                                )
                                val can = countryDao.insert(
                                    Country(
                                        id = 3,
                                        code = "CAN",
                                        name = "Canada",
                                        img = "canada"
                                    )
                                )

                                val wolf = runnerDao.insert(
                                    Runner(
                                        email = "black_volf123@gmail.com",
                                        id_gender = genderDao.getGender("MALE").id,
                                        age = 30,
                                        birthday = "1995-12-12",
                                        id_country = countryDao.getCountry("Russia").id,
                                        token = null,
                                        name = "Wolf",
                                        password = "Keker123"
                                    )
                                ).toInt()
                                val antoha = runnerDao.insert(
                                    Runner(
                                        email = "zverev_anton123@gmail.com",
                                        id_gender = genderDao.getGender("MALE").id,
                                        age = 30,
                                        birthday = "1995-12-12",
                                        id_country = countryDao.getCountry("Russia").id,
                                        token = null,
                                        name = "Antoha",
                                        password = "Keker123"
                                    )
                                ).toInt()
                                val harut = runnerDao.insert(
                                    Runner(
                                        email = "harut123@gmail.com",
                                        id_gender = genderDao.getGender("MALE").id,
                                        age = 30,
                                        birthday = "1995-12-12",
                                        id_country = countryDao.getCountry("Russia").id,
                                        token = null,
                                        name = "Harut",
                                        password = "Keker123"
                                    )
                                ).toInt()
                                val katrin = runnerDao.insert(
                                    Runner(
                                        email = "ktrn@gmail.com",
                                        id_gender = genderDao.getGender("FEMALE").id,
                                        age = 30,
                                        birthday = "1995-12-12",
                                        id_country = countryDao.getCountry("Usa").id,
                                        token = null,
                                        name = "Katrin",
                                        password = "Keker123"
                                    )
                                ).toInt()

                                marathonDao.insert(
                                    Marathon(
                                        name = "TOUR DE FRANCE",
                                        address = "Lenina street, St. Pt1",
                                        id_country = countryDao.getCountry("Russia").id,
                                        type = "Auto"
                                    )
                                )
                                marathonDao.insert(
                                    Marathon(
                                        name = "TOUR DE GLANCE",
                                        address = "Lenina street, St. Pt2",
                                        id_country = countryDao.getCountry("Canada").id,
                                        type = "Walk"
                                    )
                                )
                                marathonDao.insert(
                                    Marathon(
                                        name = "TOUR DE SIANCE",
                                        address = "Lenina street, St. Pt3",
                                        id_country = countryDao.getCountry("Usa").id,
                                        type = "Fly"
                                    )
                                )


                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex HatWearer",
                                        id_type = "Person",
                                        amount = 300,
                                        id_runner = antoha
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex HatWearer2",
                                        id_type = "Person",
                                        amount = 400,
                                        id_runner = antoha
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex HatWearer1",
                                        id_type = "Person",
                                        amount = 500,
                                        id_runner = antoha
                                    )
                                )
                                marathonDao.getMarathon("TOUR DE FRANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, antoha))
                                }
                                marathonDao.getMarathon("TOUR DE GLANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, antoha))
                                }



                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex TenderBones",
                                        id_type = "Person",
                                        amount = 300,
                                        id_runner = katrin
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex TenderBones2",
                                        id_type = "Person",
                                        amount = 400,
                                        id_runner = katrin
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex TenderBones1",
                                        id_type = "Person",
                                        amount = 500,
                                        id_runner = katrin
                                    )
                                )
                                marathonDao.getMarathon("TOUR DE FRANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, katrin))
                                }
                                marathonDao.getMarathon("TOUR DE GLANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, katrin))
                                }



                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex Leunce",
                                        id_type = "Person",
                                        amount = 300,
                                        id_runner = harut
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex Leunce2",
                                        id_type = "Person",
                                        amount = 400,
                                        id_runner = harut
                                    )
                                )
                                sponsorDao.insert(
                                    Sponsor(
                                        name = "Alex Leunce1",
                                        id_type = "Person",
                                        amount = 500,
                                        id_runner = harut
                                    )
                                )
                                marathonDao.getMarathon("TOUR DE FRANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, harut))
                                }
                                marathonDao.getMarathon("TOUR DE GLANCE").value?.id?.let { marathonId ->
                                    subscriptionDao.insert(Subscription(marathonId, harut))
                                }


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