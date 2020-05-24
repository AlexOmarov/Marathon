package ru.somarov.marathon.ui.main.plugin.runner_card.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Gender
import ru.somarov.marathon.backend.main.plugin.runner_card.entity.Runner
import ru.somarov.marathon.backend.main.plugin.runner_card.repository.RunnerCardRepo

class RunnerCardViewModel(application: Application) : AndroidViewModel(application) {

    private val runnerCardRepo: RunnerCardRepo
    val runners: LiveData<List<Runner>>
    init {

        val wordsDao = MarathonDatabase.getDatabase(application).runnerDao
        val genderDao = MarathonDatabase.getDatabase(application).genderDao
        runnerCardRepo = RunnerCardRepo(wordsDao, genderDao)
        runners = runnerCardRepo.getAllRunners()
        viewModelScope.launch {

        }
    }


        /**
        * Launching a new coroutine to insert the data in a non-blocking way
        */
        fun insert() = viewModelScope.launch {
            runnerCardRepo.insertRunner(
                Runner(email="shtil.a@yandex.ru",
                    id_gender = runnerCardRepo.getGender("MALE").id,
                    birthday = "1997-10-13",
                    countryCode = "RUS"))
        }



}
