package ru.somarov.marathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.somarov.marathon.ui.main.plugin.runner_card.fragment.RunnerCardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RunnerCardFragment.newInstance())
                    .commitNow()
        }
    }
}
