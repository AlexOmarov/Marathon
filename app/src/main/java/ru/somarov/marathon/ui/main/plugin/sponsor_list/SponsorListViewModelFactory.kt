package ru.somarov.marathon.ui.main.plugin.sponsor_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class SponsorListViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SponsorListViewModel::class.java)) {
            /*return CardViewModel(
                 = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T*/
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
