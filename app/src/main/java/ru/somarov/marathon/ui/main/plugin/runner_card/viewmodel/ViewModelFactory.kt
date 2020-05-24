package ru.somarov.marathon.ui.main.plugin.runner_card.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.somarov.marathon.backend.main.plugin.runner_card.repository.RunnerCardRepo
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repo: RunnerCardRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        /*if(modelClass.isAssignableFrom(RunnerCardViewModel::class.java)) {
            return RunnerCardViewModel(repo) as T
        }*/
        throw IllegalArgumentException("Unknown view model class")
    }
}