package ru.somarov.marathon.backend.main.core.graph

import dagger.Component
import ru.somarov.marathon.MainActivity
import javax.inject.Singleton

@Singleton
@Component
interface DependencyGraph {

    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is requesting.
    fun inject(activity: MainActivity)
}