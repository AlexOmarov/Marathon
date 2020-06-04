package ru.somarov.marathon.backend.main.plugin.runner_card

import androidx.lifecycle.LiveData
import ru.somarov.marathon.backend.main.core.db.dao.*
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource


class CardRepo(
    private val runnerDao: RunnerDao,
    private val genderDao: GenderDao,
    private val countryDao: CountryDao,
    private val marathonDao: MarathonDao,
    private val sponsorDao: SponsorDao,
    private val subscriptionDao: SubscriptionDao,
    private val remoteSource: RemoteDataSource
) {
    
    suspend fun getFreeMarathons(id: Int) = remoteSource.getFreeMarathons(id)

    // Runner in database
    suspend fun insertRunner(runner: Runner) {
        runnerDao.insert(runner)
    }

    suspend fun updateRunner(runner: Runner) {
        runnerDao.update(runner)
    }

    suspend fun deleteRunner(runner: Runner) {
        runnerDao.delete(runner)
    }
    
    // Marathon in database
    suspend fun insertMarathon(Marathon: Marathon) {
        marathonDao.insert(Marathon)
    }

    suspend fun updateMarathon(Marathon: Marathon) {
        marathonDao.update(Marathon)
    }

    suspend fun deleteMarathon(Marathon: Marathon) {
        marathonDao.delete(Marathon)
    }

    fun getMarathon(name: String): LiveData<Marathon> {
        return marathonDao.getMarathon(name)
    }

    fun getMarathons(): LiveData<List<Marathon>> {
        return marathonDao.getMarathons()
    }

    // Sponsor in database
    suspend fun insertSponsor(Sponsor: Sponsor) {
        sponsorDao.insert(Sponsor)
    }

    suspend fun updateSponsor(Sponsor: Sponsor) {
        sponsorDao.update(Sponsor)
    }

    suspend fun deleteSponsor(Sponsor: Sponsor) {
        sponsorDao.delete(Sponsor)
    }

    fun getSponsor(name: String): LiveData<Sponsor> {
        return sponsorDao.getSponsor(name)
    }

    fun getSponsor(id: Int): LiveData<Sponsor> {
        return sponsorDao.getSponsor(id)
    }


    fun getSponsors(): LiveData<List<Sponsor>> {
        return sponsorDao.getSponsors()
    }
    

    suspend fun insertGender(gender: Gender) {
        genderDao.insert(gender)
    }

    suspend fun getRunner(id: Int): LiveData<Runner> {
        return runnerDao.getRunner(id)
    }

    suspend fun getGender(name: String): Gender {
        return genderDao.getGender(name)
    }

    suspend fun getCountry(name: String): Country {
        return countryDao.getCountry(name)
    }

    fun getRunners(): LiveData<List<Runner>> {
        return runnerDao.getRunners()
    }

    suspend fun logout() {
        return runnerDao.logout()
    }

    suspend fun insertCountry(country: Country): Long {
        return countryDao.insert(country)
    }
}