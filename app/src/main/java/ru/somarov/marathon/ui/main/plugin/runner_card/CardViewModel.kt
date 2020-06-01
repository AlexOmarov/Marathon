package ru.somarov.marathon.ui.main.plugin.runner_card

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo

class CardViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepo: CardRepo =
        CardRepo(
            MarathonDatabase.getDatabase(application).runnerDao,
            MarathonDatabase.getDatabase(application).genderDao,
            RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
        )

    lateinit var runner: LiveData<Runner>

    fun setRunner(id: Int) = viewModelScope.launch {
        runner = cardRepo.getRunner(id)
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
