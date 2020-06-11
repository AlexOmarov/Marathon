package ru.somarov.marathon.ui.main.plugin.marathon_card

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo

class MarathonCardViewModel(application: Application) : AndroidViewModel(application) {

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

    var marathon: LiveData<Marathon> = MutableLiveData()
    var runners: LiveData<List<Runner>> = MutableLiveData()

    fun setupMarathonCard(id: Int) = viewModelScope.launch {
        marathon = cardRepo.getMarathon(id)
        runners = cardRepo.getRunnersSignedUpToMarathon(id)
    }

    fun logout() = viewModelScope.launch {
        cardRepo.logout()
    }

    fun signup(id_runner: Int, id_marathon: Int) = viewModelScope.launch {
        if(cardRepo.getSubscription(id_runner, id_marathon) == null) {
            cardRepo.signupToMarathon(id_runner, id_marathon)
        }
    }


    /*val marathons: LiveData<List<Marathon>>
    get() = liveData(Dispatchers.IO) {
        emit(runnerCardRepo.getMarathons().list)
    }
*/


}
