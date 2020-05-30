package ru.somarov.marathon.ui.main.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo
import java.lang.IllegalArgumentException

class ViewModelFactory<V: ViewModel> : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        /*if (modelClass.isAssignableFrom(V::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }*/
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}