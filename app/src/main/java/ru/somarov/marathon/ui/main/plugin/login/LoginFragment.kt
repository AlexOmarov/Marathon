package ru.somarov.marathon.ui.main.plugin.login

import androidx.lifecycle.ViewModelProviders
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository

import ru.somarov.marathon.databinding.LoginFragmentBinding
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModel
import ru.somarov.marathon.ui.main.core.viewmodel.AuthenticationViewModelFactory
import ru.somarov.marathon.ui.main.plugin.registration.RegistrationViewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() =
            LoginFragment()
    }

    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(requireActivity(), AuthenticationViewModelFactory(
            LoginRepository(
                runnerDao = MarathonDatabase.getDatabase(requireContext()).runnerDao,
                genderDao = MarathonDatabase.getDatabase(requireContext()).genderDao,
                countryDao = MarathonDatabase.getDatabase(requireContext()).countryDao,
                dataSource = RemoteDataSource(ServiceBuilder.buildService(RemoteService::class.java))
            )
        )
        ).get(AuthenticationViewModel::class.java)

        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false)

        binding.login.setOnClickListener {
            viewModel.login()
            val navaction = LoginFragmentDirections.loginPersonalCard(0)
            Navigation.findNavController(it).navigate(navaction)
        }

        binding.register.setOnClickListener {
            val navaction = LoginFragmentDirections.loginRegistration()
            Navigation.findNavController(it).navigate(navaction)
        }


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateUiWithUser() {
        val welcome = getString(R.string.welcome) + "Kek"
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}
