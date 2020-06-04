package ru.somarov.marathon.ui.main.plugin.sponsor_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class SponsorCardViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SponsorCardViewModel::class.java)) {
            /*return CardViewModel(
                 = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T*/
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
