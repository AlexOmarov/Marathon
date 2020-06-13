package ru.somarov.marathon.ui.main.plugin.donation

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo
import kotlin.math.sqrt

class DonationViewModel(application: Application) : AndroidViewModel(application) {

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


    var runners: LiveData<List<Runner>> = cardRepo.getRunners()
    var runner: MutableLiveData<Runner> = MutableLiveData()
    var amount: MutableLiveData<Int> = MutableLiveData(0)
    var name: MutableLiveData<String> = MutableLiveData("")


    fun makeDonation() = viewModelScope.launch {
        val id = runner.value?.id
        val amount = amount.value ?: 0
        if(amount != 0 && id != null) {
            cardRepo.insertSponsor(Sponsor(name = name.value ?: "Unknown",amount = amount,id_runner = id, id_type = "Person", id_country = 1, id_gender = "MALE"))
        }
    }
}
