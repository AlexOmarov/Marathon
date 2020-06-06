package ru.somarov.marathon.ui.main.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class AuthenticationViewModelFactory(private val loginRepository: LoginRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthenticationViewModel::class.java)) {
            return AuthenticationViewModel(
                loginRepository = loginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
