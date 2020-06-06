package ru.somarov.marathon.ui.main.plugin.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.backend.main.plugin.registration.RegistrationRepository

/**
 * ViewModel provider factory to instantiate RegistrationViewModel.
 * Required given RegistrationViewModel has a non-empty constructor
 */
class RegistrationViewModelFactory(private val registrationRepository: RegistrationRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(
                regRepo = registrationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
