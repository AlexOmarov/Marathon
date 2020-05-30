package ru.somarov.marathon.ui.main.plugin.runner_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class CardViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            /*return CardViewModel(
                 = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T*/
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
