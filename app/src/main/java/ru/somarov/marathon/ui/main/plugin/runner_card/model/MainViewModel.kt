package ru.somarov.marathon.ui.main.plugin.runner_card.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<String>()
    val user: LiveData<String>
        get() = _user
    init {
        _user.value = "John"
    }
}
