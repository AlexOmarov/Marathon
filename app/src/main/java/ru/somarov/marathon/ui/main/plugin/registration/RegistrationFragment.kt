package ru.somarov.marathon.ui.main.plugin.registration

import androidx.lifecycle.ViewModelProviders
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.remote.RemoteDataSource
import ru.somarov.marathon.backend.main.core.remote.RemoteService
import ru.somarov.marathon.backend.main.core.remote.ServiceBuilder
import ru.somarov.marathon.backend.main.plugin.login.LoginRepository
import ru.somarov.marathon.backend.main.plugin.registration.RegistrationRepository

import ru.somarov.marathon.databinding.LoginFragmentBinding
import ru.somarov.marathon.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegistrationFragment()
    }

    private lateinit var regViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        regViewModel = ViewModelProviders.of(this, RegistrationViewModelFactory (
            RegistrationRepository(
                runnerDao = MarathonDatabase.getDatabase(requireContext()).runnerDao,
                countryDao = MarathonDatabase.getDatabase(requireContext()).countryDao,
                genderDao = MarathonDatabase.getDatabase(requireContext()).genderDao
            )
        )).get(RegistrationViewModel::class.java)

        val binding: RegistrationFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.registration_fragment, container, false)

        binding.register.setOnClickListener {
            regViewModel.registration()
            val navaction = RegistrationFragmentDirections.registrationLogin()
            Navigation.findNavController(it).navigate(navaction)
        }

        regViewModel.countries.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, it)
            binding.countries.adapter = adapter
        })

        regViewModel.genders.observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, it)
            binding.genders.adapter = adapter
        })

        binding.viewModel = regViewModel
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
