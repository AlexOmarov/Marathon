package ru.somarov.marathon.ui.main.plugin.runner_list

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val cardRepo: CardRepo =
        CardRepo(
            MarathonDatabase.getDatabase(application).runnerDao,
            MarathonDatabase.getDatabase(application).genderDao,
            RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
        )


    var runners: LiveData<List<Runner>> = cardRepo.getRunners()

/*    init {
        viewModelScope.launch {
            cardRepo.insertRunner(
                Runner(email="shtil.a@yandex.ru",
                    id_gender = cardRepo.getGender("MALE").id,
                    birthday = "1997-10-13",
                    countryCode = "RUS", token = null))
            cardRepo.insertRunner(
                Runner(email="shtil2.a@yandex.ru",
                    id_gender = cardRepo.getGender("MALE").id,
                    birthday = "1997-10-13",
                    countryCode = "RUS", token = null))
            cardRepo.insertRunner(
                Runner(email="shtil3.a@yandex.ru",
                    id_gender = cardRepo.getGender("MALE").id,
                    birthday = "1997-10-13",
                    countryCode = "RUS", token = null))
        }
    }*/

    fun logout() = viewModelScope.launch {
        cardRepo.logout()
    }



}
