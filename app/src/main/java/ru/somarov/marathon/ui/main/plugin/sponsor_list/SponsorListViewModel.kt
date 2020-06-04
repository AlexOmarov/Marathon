package ru.somarov.marathon.ui.main.plugin.sponsor_list

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo

class SponsorListViewModel(application: Application) : AndroidViewModel(application) {

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

    var sponsors: LiveData<List<Sponsor>> = cardRepo.getSponsors()

    init {
        viewModelScope.launch {
            //sponsors = cardRepo.getSponsors()
        }
    }



}
