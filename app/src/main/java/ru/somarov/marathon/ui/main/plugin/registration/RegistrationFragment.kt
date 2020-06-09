package ru.somarov.marathon.ui.main.plugin.registration

import androidx.lifecycle.ViewModelProviders
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.registration_fragment.*
import ru.somarov.marathon.R
import ru.somarov.marathon.backend.main.core.db.MarathonDatabase
import ru.somarov.marathon.backend.main.core.db.entity.Country
import ru.somarov.marathon.backend.main.core.db.entity.Gender
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
            binding.countries.adapter = CountriesSpinnerAdapter(requireContext(),
                android.R.layout.simple_spinner_item, it)
        })

        binding.countries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                regViewModel.country = MutableLiveData()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                regViewModel.country.value = countries.selectedItem as Country
                println( regViewModel.gender )
            }

        }
        binding.genders.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                regViewModel.country = MutableLiveData()
                println( regViewModel.country )
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                regViewModel.gender.value = genders.selectedItem as Gender
                println( regViewModel.gender )
            }

        }

        regViewModel.genders.observe(viewLifecycleOwner, Observer {
            binding.genders.adapter = GendersSpinnerAdapter(requireContext(),
                android.R.layout.simple_spinner_item, it)
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
