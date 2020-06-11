package ru.somarov.marathon.ui.main.plugin.bmi

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.*
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.runner_card.CardRepo
import kotlin.math.sqrt

class BmiViewModel(application: Application) : AndroidViewModel(application) {

    val weight  = MutableLiveData(0)
    val height = MutableLiveData(0)
    private val mediator = MediatorLiveData<Int>()
    val index = Transformations.map(mediator) {
        return@map calculate()
    }
    val bsa = Transformations.map(mediator) {
        return@map calculateBsa()
    }

    private fun calculateBsa(): Double {
        val heightVal = height.value ?: 0
        val weightTemp = weight.value ?: 0
        var ind = 0.0
        if(heightVal != 0 && weightTemp != 0) {
            ind = sqrt(heightVal.times(weightTemp).div(3600).toDouble())
        }
        return  ind
    }

    init {
        mediator.addSource(weight) {
            mediator.value = it
        }
        mediator.addSource(height) {
            mediator.value = it
        }
    }

    private fun calculate(): String {
        val heightVal = height.value ?: 0
        val weightTemp = weight.value ?: 0
        var ind = 0
        if(heightVal != 0 && weightTemp != 0) {
            ind = weightTemp.div(heightVal.times(heightVal).div(10000))
        }
        return  getIndexDescription(ind)
    }

    private fun getIndexDescription(ind: Int): String {
        return when {
            ind < 16 -> "Острый дефицит массы"
            ind in 16..18 -> "Недостаточная масса тела"
            ind in 19..24 -> "Норма"
            ind in 25..29 -> "Избыточная масса тела"
            ind in 30..34 -> "Ожирение первой степени"
            ind in 35..40 -> "Ожирение второй степени"
            ind in 35..40 -> "Ожирение третьей степени"
            else -> "Number too high"
        }
    }
}
