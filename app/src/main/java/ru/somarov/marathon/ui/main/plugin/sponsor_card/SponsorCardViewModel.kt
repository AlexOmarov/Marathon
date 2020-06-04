package ru.somarov.marathon.ui.main.plugin.sponsor_card

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo

class SponsorCardViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepo: CardRepo =
        CardRepo(
            MarathonDatabase.getDatabase(application).runnerDao,
            MarathonDatabase.getDatabase(application).genderDao,
            MarathonDatabase.getDatabase(application).countryDao,
            MarathonDatabase.getDatabase(application).marathonDao,
            MarathonDatabase.getDatabase(application).sponsorDao,
            MarathonDatabase.getDatabase(application).subscriptionDao,
            RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
        )

    var sponsor: LiveData<Sponsor> = MutableLiveData()

    fun setSponsor(id: Int) = viewModelScope.launch {
        sponsor = cardRepo.getSponsor(id)
    }

    fun logout() = viewModelScope.launch {
        cardRepo.logout()
    }


    /*val marathons: LiveData<List<Marathon>>
    get() = liveData(Dispatchers.IO) {
        emit(runnerCardRepo.getMarathons().list)
    }
*/


}
