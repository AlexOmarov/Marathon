package ru.somarov.marathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.somarov.marathon.ui.main.plugin.login.LoginFragment
import ru.somarov.marathon.ui.main.plugin.runner_card.CardFragment

class MainActivity : AppCompatActivity() {

    // Reference to the application graph that is used across the whole app
    // val appComponent = DaggerApplicationComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow()
        }
    }
}
