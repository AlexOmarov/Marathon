package ru.somarov.marathon.ui.main.plugin.runner_card.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _user = MutableLiveData<String>()
    private val secondName = "The most beautiful man on Earth"
    private val firstName = "John"
    val user: LiveData<String>
        get() = _user
    init {
        _user.value = firstName
    }

    fun changeName() {
        if (secondName == _user.value) _user.value = firstName else _user.value = secondName
    }
}
