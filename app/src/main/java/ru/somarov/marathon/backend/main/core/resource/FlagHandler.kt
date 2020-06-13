package ru.somarov.marathon.backend.main.core.resource

import ru.somarov.marathon.R

fun handle(id_country: Int): Int {
    return when(id_country) {
        1 -> R.drawable.ic_russia
        2 -> R.drawable.ic_american_samoa
        3 -> R.drawable.ic_canada
        else ->  R.drawable.ic_russia
    }
}