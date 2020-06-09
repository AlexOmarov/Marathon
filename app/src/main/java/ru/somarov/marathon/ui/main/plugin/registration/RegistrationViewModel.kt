package ru.somarov.marathon.ui.main.plugin.registration

import android.util.Patterns
import android.view.View
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender
import ru.somarov.marathon.backend.main.core.db.entity.Runner
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.backend.main.plugin.registration.RegistrationRepository


class RegistrationViewModel(private val regRepo: RegistrationRepository) : ViewModel() {

    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val birthday = MutableLiveData("")
    val password = MutableLiveData("")
    val cnfPassword = MutableLiveData("")
    val age = MutableLiveData(0)
    val token = MutableLiveData("")
    var result: LiveData<Runner> = MutableLiveData()
    var genders: LiveData<List<Gender>> = regRepo.getGenders()
    var gender: MutableLiveData<Gender> = MutableLiveData()
    var countries: LiveData<List<Country>> = regRepo.getCountries()
    var country: MutableLiveData<Country> = MutableLiveData()


    private val _registrationInProcess = MutableLiveData(false)
    private val registrationInProcess: LiveData<Boolean> = _registrationInProcess

    private val usernamePasswordListener = MediatorLiveData<String>()

    val registrationAllowed: LiveData<Boolean> = Transformations.map(usernamePasswordListener) {
        return@map isRegistrationAllowed()
    }

    val progressBarEnabled: LiveData<Int> =
        Transformations.map(registrationInProcess) { result ->
            if (result) {
                return@map View.VISIBLE
            } else {
                return@map View.GONE
            }
        }

    init {

        usernamePasswordListener.addSource(email) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(password) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(gender) {
            usernamePasswordListener.value = it.toString()
        }
        usernamePasswordListener.addSource(birthday) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(name) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(cnfPassword) {
            usernamePasswordListener.value = it
        }
        usernamePasswordListener.addSource(age) {
            usernamePasswordListener.value = it.toString()
        }
        usernamePasswordListener.addSource(country) {
            usernamePasswordListener.value = it.toString()
        }
    }

    private fun isRegistrationAllowed(): Boolean {
        return password.value?.length ?: 0 > 5
                && Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()
                && password.value == cnfPassword.value
                && name.value != null
                && gender.value != null
                && birthday.value != null
                && age.value ?: 0 > 18
                && country.value != null
    }

    fun registration() = viewModelScope.launch {
        if (isRegistrationAllowed()) {
            println(
                email.value.toString() + ", " +
                        name.value.toString() + ", " +
                        gender.value.toString() + ", " +
                        birthday.value.toString() + ", " +
                        password.value.toString() + ", " +
                        age.value.toString() +  ", " +
                        country.value.toString() + ", " +
                        token.value.toString())
            result = regRepo.register(
                email.value.toString(),
                name.value.toString(),
                gender.value?.id.toString(),
                birthday.value.toString(),
                password.value.toString(),
                age.value ?: 0,
                country.value?.id ?: 0,
                token.value.toString()
            )
        }
    }
}
