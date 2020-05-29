package ru.somarov.marathon.ui.main.plugin.runner_card.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.repository.RunnerCardRepo

class RunnerCardViewModel(application: Application) : AndroidViewModel(application) {

    private val runnerCardRepo: RunnerCardRepo = RunnerCardRepo(
        MarathonDatabase.getDatabase(application).runnerDao,
        MarathonDatabase.getDatabase(application).genderDao,
        RemoteDataSource(
            ServiceBuilder.buildService(
                RemoteService::class.java)
        )
    )

    val runners: LiveData<List<Runner>>
    /*val marathons: LiveData<List<Marathon>>*/
    /*val sponsors: LiveData<List<Sponsor>>
    val organizers: LiveData<List<Organizer>>
    val subscriptions: LiveData<List<Subscription>>*/

    init {
        runners = runnerCardRepo.getAllRunners()
    }

    fun insert() = viewModelScope.launch {
        runnerCardRepo.insertRunner(
            Runner(email="shtil.a@yandex.ru",
                id_gender = runnerCardRepo.getGender("MALE").id,
                birthday = "1997-10-13",
                countryCode = "RUS", password = "password"))
    }



}
