package ru.somarov.marathon.ui.main.core.viewmodel

import android.util.Patterns
import android.view.View
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository

class AuthenticationViewModel(private val loginRepository: LoginRepository): ViewModel() {

    val password = MutableLiveData("")
    val username = MutableLiveData("")


    private val _loginInProcess = MutableLiveData(false)
    private val loginInProcess: LiveData<Boolean> = _loginInProcess

    private val usernamePasswordListener = MediatorLiveData<String>()

    var runner: LiveData<Runner> = MutableLiveData()

    val loginAllowed: LiveData<Boolean> = Transformations.map(usernamePasswordListener) {
        return@map isLoginAllowed()
    }

    val progressBarEnabled: LiveData<Int> =
        Transformations.map(loginInProcess) { result ->
            if(result) {
                return@map View.VISIBLE
            } else {
                return@map View.GONE
            }
        }

    init {
        usernamePasswordListener.addSource(username) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(password) {
            usernamePasswordListener.value = it
        }
    }

    private fun isLoginAllowed(): Boolean {
        return password.value?.length ?: 0 > 5 && Patterns.EMAIL_ADDRESS.matcher(username.value.toString())
            .matches()
    }

    fun login()  = viewModelScope.launch {
        _loginInProcess.value = true
        val login = username.value
        val passwd = password.value
        if(passwd != null && login != null) {
            runner = loginRepository.login(login, passwd)
        }
        _loginInProcess.value = false
    }
}