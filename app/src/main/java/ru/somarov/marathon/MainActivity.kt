package ru.somarov.marathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.main_activity.*
import ru.somarov.marathon.ui.main.plugin.login.LoginFragment
import ru.somarov.marathon.ui.main.plugin.runner_card.CardFragment

class MainActivity : AppCompatActivity() {

    // Reference to the application graph that is used across the whole app
    // val appComponent = DaggerApplicationComponent.create()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setupBottomNavMenu(navController)
        setupSideNavigationMenu(navController)
        setupActionBar(navController)

        if (savedInstanceState == null) {
            /*supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_fade_exit,
                                R.anim.fragment_fast_out_extra_slow_in, R.anim.fragment_open_enter)
                    .add(R.id.nav_host_fragment, LoginFragment.newInstance())
                    .commitNow()*/
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setupSideNavigationMenu(navController: NavController) {
        nav_view?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }


    private fun setupActionBar(navController: NavController) {
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        return NavigationUI.navigateUp(navController, drawer_layout)
    }
}
